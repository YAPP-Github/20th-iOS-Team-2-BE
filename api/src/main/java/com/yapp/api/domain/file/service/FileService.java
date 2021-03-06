package com.yapp.api.domain.file.service;

import static com.yapp.core.error.exception.ErrorCode.*;
import static com.yapp.core.persistance.file.persistence.entity.File.*;
import static java.util.Comparator.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.core.error.exception.BaseBusinessException;
import com.yapp.core.persistance.album.element.folder.persistence.entity.Album;
import com.yapp.core.persistance.file.persistence.entity.File;
import com.yapp.core.persistance.file.persistence.handler.FileCommandHandler;
import com.yapp.core.persistance.file.persistence.handler.FileQueryHandler;
import com.yapp.core.persistance.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileService {

	private final FileCommandHandler fileCommandHandler;
	private final FileQueryHandler fileQueryHandler;

	public List<File> getFiles(User user, String kind) {
		if (kind.equals(FAVOURITE)) {
			return fileQueryHandler.findList(fileRepository -> fileRepository.findAllByFamilyAndFavourite(user.getFamily(),
																										  true));
		}
		return fileQueryHandler.findList(fileRepository -> fileRepository.findAllByFamilyAndKind(user.getFamily(),
																								 kind))
							   .stream()
							   .sorted(comparing(File::getDateTime).reversed())
							   .collect(Collectors.toList());
	}

	public Page<File> getFiles(User user, Album album, Pageable pageable) {
		return fileQueryHandler.findPage(fileRepository -> fileRepository.findAllByFamilyAndAlbum(user.getFamily(),
																								  album,
																								  pageable));
	}

	public Page<File> getFiles(User user, String kind, Pageable pageable) {
		if (kind.equalsIgnoreCase("favourite")) {
			return fileQueryHandler.findPage(fileRepository -> fileRepository.findAllByFamilyAndFavourite(user.getFamily(),
																										  true,
																										  pageable));
		}
		return fileQueryHandler.findPage(fileRepository -> fileRepository.findAllByFamilyAndKind(user.getFamily(),
																								 Kind.valueOf(kind),
																								 pageable));
	}

	// ????????? ?????? ??????
	@Transactional
	public void remove(User user, Long fileId) {
		File file = fileQueryHandler.findOne(fileRepository -> fileRepository.findByFamilyAndId(user.getFamily(),
																								fileId))
									.orElseThrow(() -> new BaseBusinessException(FILE_NOT_FOUND,
																				 new RuntimeException(
																					 "FileNotFoundError : which {fileId} in DELETE /album/{fileId}")));
		fileCommandHandler.removeOne(fileRepository -> fileRepository.delete(file));
	}

	// ????????? ?????? ??????
	@Transactional
	public boolean makeFavourite(User user, Long fileId) {
		return fileQueryHandler.findOne(fileRepository -> fileRepository.findById(fileId))
							   .orElseThrow(() -> new BaseBusinessException(FILE_NOT_FOUND,
																			new RuntimeException(
																				"FileNotFoundError : which ?fileId in POST /album/favourite?fileId")))
							   .doFavour();
	}
}
