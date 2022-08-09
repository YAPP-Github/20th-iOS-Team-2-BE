package com.yapp.api.domain.album.service;

import static com.yapp.core.error.exception.ErrorCode.*;
import static java.util.Comparator.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.yapp.core.constant.FileKind;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.core.persistence.folder.album.persistence.entity.Album;
import com.yapp.core.persistence.folder.album.persistence.handler.AlbumCommandHandler;
import com.yapp.core.persistence.folder.album.persistence.handler.AlbumQueryHandler;
import com.yapp.core.persistence.family.persistence.handler.FamilyQueryHandler;
import com.yapp.core.persistence.file.persistence.entity.File;
import com.yapp.core.persistence.file.persistence.handler.FileCommandHandler;
import com.yapp.core.persistence.file.persistence.handler.FileQueryHandler;
import com.yapp.core.persistence.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlbumService {
	private static final int FIRST_INDEX = 0;

	private final AlbumCommandHandler albumCommandHandler;
	private final AlbumQueryHandler albumQueryHandler;
	private final FileCommandHandler fileCommandHandler;
	private final FileQueryHandler fileQueryHandler;
	private final FamilyQueryHandler familyQueryHandler;

	public Album get(User user, Long albumId) {
		return albumQueryHandler.findOne(albumRepository -> albumRepository.findByFamilyAndId(user.getFamily(),
																								albumId))
								.orElseThrow(() -> new BaseBusinessException(FILE_NOT_FOUND,
																			 new RuntimeException(
																				 "FileNotFoundErrorCode : which {albumId} in GET /albums/details/{albumId}")));
	}

	public List<Album> getList(User user) {
		return albumQueryHandler.findAll(albumRepository -> albumRepository.findByFamilyOrderByDateDesc(user.getFamily()));
	}

	public Page<Album> getList(User user, Pageable pageable) {
		return albumQueryHandler.findAllAsPage(albumRepository -> albumRepository.findAllByFamilyOrderByDateDesc(
			pageable,
			user.getFamily()));
	}

	public Map<String, KindInfo> getCountForEachCategory(User user) {
		HashMap<FileKind, List<File>> kindInfoMap = new HashMap<>();
		kindInfoMap.put(FileKind.PHOTO, new ArrayList<>());
		kindInfoMap.put(FileKind.RECORDING, new ArrayList<>());

		List<File> files = fileQueryHandler.findAll(fileRepository -> fileRepository.findAllByFamily(user.getFamily()));

		files.forEach(file -> {
			if (file.isPhoto()) {
				kindInfoMap.get(FileKind.PHOTO)
						   .add(file);
			}
			if (file.isRecording()) {
				kindInfoMap.get(FileKind.RECORDING)
						   .add(file);
			}
		});

		return Map.of(File.FAVOURITE,
					  new KindInfo((int)files.stream()
											 .filter(File::isFavourite)
											 .count(),
								   files.stream()
										.filter(File::isFavourite)
										.findFirst()
										.orElseGet(() -> File.INVALID)
										.getLink()),
					  File.KIND_PHOTO,
					  new KindInfo(kindInfoMap.get(File.KIND_PHOTO)
											  .size(),
								   kindInfoMap.get(File.KIND_PHOTO)
											  .stream()
											  .max(comparing(File::getDateTime))
											  .orElse(File.INVALID)
											  .getLink()),
					  File.KIND_RECORDING,
					  new KindInfo(kindInfoMap.get(File.KIND_RECORDING)
											  .size(),
								   kindInfoMap.get(File.KIND_RECORDING)
											  .stream()
											  .max(comparing(File::getDateTime))
											  .orElse(File.INVALID)
											  .getLink()));
	}

	@Transactional
	public void uploadPhotos(User user, LocalDateTime dateTime, List<String> photos) {
		Album album = albumQueryHandler.findAlbumByDate(dateTime.toLocalDate())
									   .orElseGet(() -> new Album(user.getFamily(), dateTime.toLocalDate()));

		fileCommandHandler.create(fileRepository -> fileRepository.saveAll(photos.stream()
																			   .map(link -> File.of("-",
																									link,
																									File.KIND_PHOTO,
																									album,
																									dateTime,
																									user.getFamily()))
																			   .collect(Collectors.toList())));

		if (album.noThumbnail()) {
			album.setThumbnail(photos.get(FIRST_INDEX));
			albumCommandHandler.create(repository -> repository.save(album));
		}
	}

	@Transactional
	public void uploadRecordings(User user, LocalDateTime dateTime, String title, String link) {
		Album album = albumQueryHandler.findAlbumByDate(dateTime.toLocalDate())
									   .orElseGet(() -> new Album(user.getFamily(), dateTime.toLocalDate()));

		fileCommandHandler.create(fileRepository -> fileRepository.save(File.of(title,
																			  link,
																			  File.KIND_RECORDING,
																			  album,
																			  dateTime,
																			  user.getFamily())));

		if (album.noThumbnail()) {
			// album.setThumbnail("default image");
			albumCommandHandler.create(repository -> repository.save(album));
		}
	}

	@Transactional
	public void modifyTitle(User user, Long albumId, String toBe) {
		Album album = getAlbumByUserAndId(user, albumId);
		album.modifyTitle(toBe);
	}

	@Transactional
	public void modifyDate(User user, Long albumId, LocalDateTime date) {
		Album album = getAlbumByUserAndId(user, albumId);
		album.modifyDate(date);
	}

	// 비동기 처리 예정
	@Transactional
	public void remove(User user, Long albumId) {
		Album album = getAlbumByUserAndId(user, albumId);
		albumCommandHandler.remove(albumRepository -> albumRepository.delete(album));
	}

	private Album getAlbumByUserAndId(User user, Long albumId) {
		Album album = albumQueryHandler.findOne(albumRepository -> albumRepository.findByFamilyAndId(user.getFamily(),
																									   albumId))
									   .orElseThrow(() -> new BaseBusinessException(ALBUM_NOT_FOUND,
																					new RuntimeException(
																						"albumNotFoundError : which {albumId} in PATCH /album/{albumId}")));
		return album;
	}

	@Transactional
	public void delegate(User user, Long albumId, Long fileId) {
		Album album = albumQueryHandler.findOne(albumRepository -> albumRepository.findByFamilyAndId(user.getFamily(),
																									   albumId))
									   .orElseThrow(() -> new BaseBusinessException(ALBUM_NOT_FOUND));
		File file = fileQueryHandler.findOne(fileRepository -> fileRepository.findById(fileId))
									.orElseThrow(() -> new BaseBusinessException(FILE_NOT_FOUND));

		if (album.contains(file) && file.isPhoto()) {
			album.setThumbnail(file.getLink());
			return ;
		}

		throw new BaseBusinessException(ALBUM_FILE_NOT_MATCH);
	}

	@Transactional
	public void modifyFileDate(User user, Long fileId, LocalDateTime dateTime) {
		File file = fileQueryHandler.findOne(fileRepository -> fileRepository.findById(fileId))
									.orElseThrow(() -> new BaseBusinessException(FILE_NOT_FOUND));
		file.modifyDate(dateTime);

		Album album = albumQueryHandler.findAlbumByDate(dateTime.toLocalDate())
									   .orElseGet(() -> new Album(user.getFamily(), dateTime.toLocalDate()));

		file.modifyAlbum(album);
		fileCommandHandler.create(fileRepository -> fileRepository.save(file));
		albumCommandHandler.create(albumRepository -> albumRepository.save(album));
	}

	@Getter
	@NoArgsConstructor(access = PROTECTED)
	@AllArgsConstructor
	public static class KindInfo {
		private int count;
		private String link = "";
	}
}
