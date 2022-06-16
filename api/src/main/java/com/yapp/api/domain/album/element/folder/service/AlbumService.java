package com.yapp.api.domain.album.element.folder.service;

import static com.yapp.api.domain.file.persistence.entity.File.*;
import static com.yapp.core.error.exception.ErrorCode.*;

import java.time.LocalDate;
import java.util.Comparator;
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
								.sorted(Comparator.comparing(Album::getDate)
												  .reversed())
								.collect(Collectors.toList());
	}

	public Map<String, Integer> getCountForEachCategory(User user) {
		return Map.of(FAVOURITE,
					  albumQueryHandler.countByKind(albumRepository -> albumRepository.countByFavourite(user.getFamily(),
																										true)),
					  KIND_PHOTO,
					  albumQueryHandler.countByKind(albumRepository -> albumRepository.countByKind(user.getFamily(),
																								   KIND_PHOTO)),
					  KIND_RECORDING,
					  albumQueryHandler.countByKind(albumRepository -> albumRepository.countByKind(user.getFamily(),
																								   KIND_RECORDING)));
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
}
