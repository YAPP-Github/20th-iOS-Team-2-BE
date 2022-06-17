package com.yapp.api.domain.file.service;

import static com.yapp.core.error.exception.ErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.file.persistence.handler.FileCommandHandler;
import com.yapp.api.domain.file.persistence.handler.FileQueryHandler;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.core.error.exception.BaseBusinessException;
import com.yapp.core.error.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileService {

	private final FileCommandHandler fileCommandHandler;
	private final FileQueryHandler fileQueryHandler;

	// 비동기 처리 예정
	@Transactional
	public void remove(User user, Long fileId) {
		File file = fileQueryHandler.findOne(fileRepository -> fileRepository.findByFamilyAndId(user.getFamily(),
																								fileId))
									.orElseThrow(() -> new BaseBusinessException(FILE_NOT_FOUND,
																				 new RuntimeException(
																					 "FileNotFoundError : which {fileId} in DELETE /album/{fileId}")));
		fileCommandHandler.removeOne(fileRepository -> fileRepository.delete(file));
	}
}
