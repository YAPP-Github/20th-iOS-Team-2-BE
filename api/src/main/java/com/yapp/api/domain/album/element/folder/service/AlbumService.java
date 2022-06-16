package com.yapp.api.domain.album.element.folder.service;

import static com.yapp.api.domain.file.persistence.entity.File.*;
import static com.yapp.core.error.exception.ErrorCode.*;
import static java.util.Comparator.*;
import static lombok.AccessLevel.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.api.domain.album.element.folder.persistence.entity.Album;
import com.yapp.api.domain.album.element.folder.persistence.handler.AlbumCommandHandler;
import com.yapp.api.domain.album.element.folder.persistence.handler.AlbumQueryHandler;
import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.file.persistence.handler.FileCommandHandler;
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

	public Album get(User user, Long albumId) {
		return albumQueryHandler.findAlbum(albumRepository -> albumRepository.findByFamilyAndId(user.getFamily(),
																								albumId))
								.orElseThrow(() -> new BaseBusinessException(FILE_NOT_FOUND,
																			 new RuntimeException(
																				 "FileNotFoundErrorCode : which {albumId} in GET /albums/details/{albumId}")));
	}

	public List<Album> getList(User user) {
		return albumQueryHandler.findAll(albumRepository -> albumRepository.findByFamily(user.getFamily()))
								.stream()
								.sorted(comparing(Album::getDate).reversed())
								.collect(Collectors.toList());
	}

	public Map<String, KindInfo> getCountForEachCategory(User user) {
		HashMap<String, List<File>> kindInfoMap = new HashMap<>();
		kindInfoMap.put(KIND_PHOTO, new ArrayList<>());
		kindInfoMap.put(KIND_RECORDING, new ArrayList<>());

		List<File> files = fileCommandHandler.findList(fileRepository -> fileRepository.findAllByFamily(user.getFamily()));

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
											  .max(comparing(File::getDate))
											  .orElse(INVALID)
											  .getLink()),
					  KIND_RECORDING,
					  new KindInfo(kindInfoMap.get(KIND_RECORDING)
											  .size(),
								   kindInfoMap.get(KIND_RECORDING)
											  .stream()
											  .max(comparing(File::getDate))
											  .orElse(INVALID)
											  .getLink()));
	}

	public List<File> getFiles(User user, String kind) {
		if (kind.equals(FAVOURITE)) {
			return fileCommandHandler.findList(fileRepository -> fileRepository.findAllByFamilyAndFavourite(user.getFamily(),
																											true));
		}
		return fileCommandHandler.findList(fileRepository -> fileRepository.findAllByFamilyAndKind(user.getFamily(),
																								   kind))
								 .stream()
								 .sorted(comparing(File::getDate).reversed())
								 .collect(Collectors.toList());
	}

	// 비동기처리 예정
	@Transactional
	public void uploadPhotos(User user, LocalDate date, List<String> photos) {
		Album album = albumQueryHandler.findAlbumByDate(date)
									   .orElseGet(() -> new Album(user.getFamily(), date));

		fileCommandHandler.save(fileRepository -> fileRepository.saveAll(photos.stream()
																			   .map(link -> File.of("-",
																									link,
																									KIND_PHOTO,
																									album,
																									date,
																									user.getFamily()))
																			   .collect(Collectors.toList())));

		if (album.noThumbnail()) {
			album.setThumbnail(photos.get(FIRST_INDEX));
			albumCommandHandler.saveAlbum(repository -> repository.save(album));
		}
	}

	// 비동기 처리 예정
	@Transactional
	public void uploadRecordings(User user, LocalDate date, String title, String link) {
		Album album = albumQueryHandler.findAlbumByDate(date)
									   .orElseGet(() -> new Album(user.getFamily(), date));

		fileCommandHandler.save(fileRepository -> fileRepository.save(File.of(title,
																			  link,
																			  KIND_RECORDING,
																			  album,
																			  date,
																			  user.getFamily())));

		if (album.noThumbnail()) {
			// album.setThumbnail("default image");
			albumCommandHandler.saveAlbum(repository -> repository.save(album));
		}
	}

	// 비동기 처리 예정
	@Transactional
	public void makeFavourite(User user, Long fileId) {
		fileCommandHandler.findOne(fileRepository -> fileRepository.findById(fileId))
						  .orElseThrow(() -> new BaseBusinessException(FILE_NOT_FOUND,
																	   new RuntimeException(
																		   "FileNotFoundError : which ?fileId in POST /album/favourite?fileId")))
						  .doFavour();
	}

	@Getter
	@NoArgsConstructor(access = PROTECTED)
	@AllArgsConstructor
	public static class KindInfo {
		private int count;
		private String link = "";
	}
}
