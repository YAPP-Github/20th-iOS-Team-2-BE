package com.yapp.api.domain.album.element.folder.service;

import static com.yapp.api.domain.file.persistence.entity.File.*;
import static com.yapp.core.error.exception.ErrorCode.*;
import static java.util.Comparator.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.api.domain.album.element.folder.persistence.entity.Album;
import com.yapp.api.domain.album.element.folder.persistence.handler.AlbumCommandHandler;
import com.yapp.api.domain.album.element.folder.persistence.handler.AlbumQueryHandler;
import com.yapp.api.domain.family.persistence.handler.FamilyQueryHandler;
import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.file.persistence.handler.FileCommandHandler;
import com.yapp.api.domain.file.persistence.handler.FileQueryHandler;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.core.error.exception.BaseBusinessException;

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
		return albumQueryHandler.findAlbum(albumRepository -> albumRepository.findByFamilyAndId(user.getFamily(),
																								albumId))
								.orElseThrow(() -> new BaseBusinessException(FILE_NOT_FOUND,
																			 new RuntimeException(
																				 "FileNotFoundErrorCode : which {albumId} in GET /albums/details/{albumId}")));
	}

	public List<Album> getList(User user) {
		return albumQueryHandler.findAll(albumRepository -> albumRepository.findByFamilyOrderByDateTimeDesc(user.getFamily()));
	}

	public Page<Album> getList(User user, Pageable pageable) {
		return albumQueryHandler.findAllAsPage(albumRepository -> albumRepository.findAllByFamilyOrderByDateTimeDesc(
			pageable,
			user.getFamily()));
	}

	public Map<String, KindInfo> getCountForEachCategory(User user) {
		HashMap<String, List<File>> kindInfoMap = new HashMap<>();
		kindInfoMap.put(KIND_PHOTO, new ArrayList<>());
		kindInfoMap.put(KIND_RECORDING, new ArrayList<>());

		List<File> files = fileQueryHandler.findList(fileRepository -> fileRepository.findAllByFamily(user.getFamily()));

		files.forEach(file -> {
			if (file.isPhoto()) {
				kindInfoMap.get(KIND_PHOTO)
						   .add(file);
			}
			if (file.isRecording()) {
				kindInfoMap.get(KIND_RECORDING)
						   .add(file);
			}
		});

		return Map.of(FAVOURITE,
					  new KindInfo((int)files.stream()
											 .filter(File::isFavourite)
											 .count(),
								   files.stream()
										.filter(File::isFavourite)
										.findFirst()
										.orElseGet(() -> INVALID)
										.getLink()),
					  KIND_PHOTO,
					  new KindInfo(kindInfoMap.get(KIND_PHOTO)
											  .size(),
								   kindInfoMap.get(KIND_PHOTO)
											  .stream()
											  .max(comparing(File::getDateTime))
											  .orElse(INVALID)
											  .getLink()),
					  KIND_RECORDING,
					  new KindInfo(kindInfoMap.get(KIND_RECORDING)
											  .size(),
								   kindInfoMap.get(KIND_RECORDING)
											  .stream()
											  .max(comparing(File::getDateTime))
											  .orElse(INVALID)
											  .getLink()));
	}

	@Transactional
	public void uploadPhotos(User user, LocalDateTime dateTime, List<String> photos) {
		Album album = albumQueryHandler.findAlbumByDateTime(dateTime)
									   .orElseGet(() -> new Album(user.getFamily(), dateTime));

		fileCommandHandler.save(fileRepository -> fileRepository.saveAll(photos.stream()
																			   .map(link -> File.of("-",
																									link,
																									KIND_PHOTO,
																									album,
																									dateTime,
																									user.getFamily()))
																			   .collect(Collectors.toList())));

		if (album.noThumbnail()) {
			album.setThumbnail(photos.get(FIRST_INDEX));
			albumCommandHandler.saveAlbum(repository -> repository.save(album));
		}
	}

	@Transactional
	public void uploadRecordings(User user, LocalDateTime dateTime, String title, String link) {
		Album album = albumQueryHandler.findAlbumByDateTime(dateTime)
									   .orElseGet(() -> new Album(user.getFamily(), dateTime));

		fileCommandHandler.save(fileRepository -> fileRepository.save(File.of(title,
																			  link,
																			  KIND_RECORDING,
																			  album,
																			  dateTime,
																			  user.getFamily())));

		if (album.noThumbnail()) {
			// album.setThumbnail("default image");
			albumCommandHandler.saveAlbum(repository -> repository.save(album));
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
		albumCommandHandler.removeOne(albumRepository -> albumRepository.delete(album));
	}

	private Album getAlbumByUserAndId(User user, Long albumId) {
		Album album = albumQueryHandler.findAlbum(albumRepository -> albumRepository.findByFamilyAndId(user.getFamily(),
																									   albumId))
									   .orElseThrow(() -> new BaseBusinessException(ALBUM_NOT_FOUND,
																					new RuntimeException(
																						"albumNotFoundError : which {albumId} in PATCH /album/{albumId}")));
		return album;
	}

	@Getter
	@NoArgsConstructor(access = PROTECTED)
	@AllArgsConstructor
	public static class KindInfo {
		private int count;
		private String link = "";
	}
}
