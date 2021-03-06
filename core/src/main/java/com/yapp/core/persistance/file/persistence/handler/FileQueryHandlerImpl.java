package com.yapp.core.persistance.file.persistence.handler;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.yapp.core.persistance.file.persistence.entity.File;
import com.yapp.core.persistance.file.persistence.repository.FileRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FileQueryHandlerImpl implements FileQueryHandler {
	private final FileRepository fileRepository;

	@Override
	public List<File> findList(Function<FileRepository, List<File>> function) {
		return function.apply(fileRepository);
	}

	@Override
	public Optional<File> findOne(Function<FileRepository, Optional<File>> function) {
		return function.apply(fileRepository);
	}

	@Override
	public Page<File> findPage(Function<FileRepository, Page<File>> function) {
		return function.apply(fileRepository);
	}
}
