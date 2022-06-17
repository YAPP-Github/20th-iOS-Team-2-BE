package com.yapp.api.domain.file.persistence.handler;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.file.persistence.repository.FileRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FileQueryHandlerImpl implements FileQueryHandler {
	private final FileRepository fileRepository;

	@Override
	public Optional<File> findOne(Function<FileRepository, Optional<File>> function) {
		return function.apply(fileRepository);
	}
}
