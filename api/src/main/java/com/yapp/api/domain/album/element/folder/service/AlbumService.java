package com.yapp.api.domain.album.element.folder.service;

import static com.yapp.api.domain.file.persistence.entity.File.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.api.domain.album.element.folder.persistence.entity.Album;
import com.yapp.api.domain.album.element.folder.persistence.handler.AlbumCommandHandler;
import com.yapp.api.domain.album.element.folder.persistence.handler.AlbumQueryHandler;
import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.file.persistence.handler.FileCommandHandler;
import com.yapp.api.domain.user.persistence.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlbumService {
	private static final int FIRST_INDEX = 0;

	private final AlbumCommandHandler albumCommandHandler;
	private final AlbumQueryHandler albumQueryHandler;
	private final FileCommandHandler fileCommandHandler;

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
																									date))
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
																			  date)));

		if (album.noThumbnail()) {
			// album.setThumbnail("default image");
			albumCommandHandler.saveAlbum(repository -> repository.save(album));
		}
	}
}
