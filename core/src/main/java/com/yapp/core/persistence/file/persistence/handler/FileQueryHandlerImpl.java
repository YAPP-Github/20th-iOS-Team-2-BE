package com.yapp.core.persistence.file.persistence.handler;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.yapp.core.persistence.file.persistence.entity.File;
import com.yapp.core.persistence.file.persistence.repository.FileRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FileQueryHandlerImpl implements FileQueryHandler {
	private final FileRepository fileRepository;

	@Override
	public List<File> findAll(Function<FileRepository, List<File>> function) {
		return function.apply(fileRepository);
	}

	@Override
	public Optional<File> findOne(Function<FileRepository, Optional<File>> function) {
		return function.apply(fileRepository);
	}

	@Override
	public Page<File> findAllAsPage(Function<FileRepository, Page<File>> function) {
		return function.apply(fileRepository);
	}
}
