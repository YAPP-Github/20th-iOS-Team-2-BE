package com.yapp.core.persistance.file.persistence.handler;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.yapp.core.persistance.file.persistence.repository.FileRepository;

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
	public void removeOne(Consumer<FileRepository> consumer) {
		consumer.accept(fileRepository);
	}
}
