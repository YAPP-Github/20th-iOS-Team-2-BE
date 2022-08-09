package com.yapp.core.persistence.file.persistence.handler;

import java.util.function.Consumer;

import com.yapp.core.persistence.file.persistence.repository.FileRepository;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FileCommandHandlerImpl implements FileCommandHandler {
	private final FileRepository fileRepository;

	@Override
	public void create(Consumer<FileRepository> consumer) {
		consumer.accept(fileRepository);
	}

	@Override
	public void remove(Consumer<FileRepository> consumer) {
		consumer.accept(fileRepository);
	}
}
