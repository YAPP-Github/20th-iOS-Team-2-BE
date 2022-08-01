package com.yapp.core.persistance.file.persistence.handler;

import java.util.function.Consumer;

import com.yapp.core.persistance.file.persistence.repository.FileRepository;

public interface FileCommandHandler {
	void save(Consumer<FileRepository> consumer);

	void removeOne(Consumer<FileRepository> consumer);
}
