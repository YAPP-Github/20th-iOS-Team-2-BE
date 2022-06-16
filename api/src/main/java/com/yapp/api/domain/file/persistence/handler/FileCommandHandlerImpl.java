package com.yapp.api.domain.file.persistence.handler;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.file.persistence.repository.FileRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FileCommandHandlerImpl implements FileCommandHandler {
	private final FileRepository fileRepository;

	@Override
	public void save(Consumer<FileRepository> consumer) {
		consumer.accept(fileRepository);
	}

	@Override
	public List<File> findList(Function<FileRepository, List<File>> function) {
		return function.apply(fileRepository);
	}

	@Override
	public Optional<File> findOne(Function<FileRepository, Optional<File>> function) {
		return function.apply(fileRepository);
	}
}
