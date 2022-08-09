package com.yapp.core.persistence.file.persistence.handler;

import java.util.function.Consumer;

import com.yapp.core.persistence.file.persistence.repository.FileRepository;

public interface FileCommandHandler {
	void create(Consumer<FileRepository> consumer);

	void remove(Consumer<FileRepository> consumer);
}
