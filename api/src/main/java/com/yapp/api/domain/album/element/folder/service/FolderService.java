package com.yapp.api.domain.album.element.folder.service;

import static com.yapp.api.domain.file.persistence.entity.File.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.api.domain.album.element.folder.persistence.entity.Folder;
import com.yapp.api.domain.album.element.folder.persistence.handler.FolderCommandHandler;
import com.yapp.api.domain.album.element.folder.persistence.handler.FolderQueryHandler;
import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.file.persistence.handler.FileCommandHandler;
import com.yapp.api.domain.user.persistence.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FolderService {
	private static final int FIRST_INDEX = 0;

	private final FolderCommandHandler folderCommandHandler;
	private final FolderQueryHandler folderQueryHandler;
	private final FileCommandHandler fileCommandHandler;

	// 비동기처리 예정
	@Transactional
	public void uploadPhotos(User user, LocalDate date, List<String> photos) {
		Folder album = folderQueryHandler.findFolderByDate(date)
										 .orElseGet(() -> new Folder(user.getFamily(), date));

		fileCommandHandler.save(fileRepository -> fileRepository.saveAll(photos.stream()
																			   .map(link -> File.of("-",
																									link,
																									KIND_PHOTO,
																									album,
																									date))
																			   .collect(Collectors.toList())));

		if (album.noThumbnail()) {
			album.setThumbnail(photos.get(FIRST_INDEX));
			folderCommandHandler.saveFolder(repository -> repository.save(album));
		}
	}

	// 비동기 처리 예정
	@Transactional
	public void uploadRecordings(User user, LocalDate date, String title, String link) {
		Folder album = folderQueryHandler.findFolderByDate(date)
										 .orElseGet(() -> new Folder(user.getFamily(), date));

		fileCommandHandler.save(fileRepository -> fileRepository.save(File.of(title,
																			  link,
																			  KIND_RECORDING,
																			  album,
																			  date)));

		if (album.noThumbnail()) {
			// album.setThumbnail("default image");
			folderCommandHandler.saveFolder(repository -> repository.save(album));
		}
	}
}
