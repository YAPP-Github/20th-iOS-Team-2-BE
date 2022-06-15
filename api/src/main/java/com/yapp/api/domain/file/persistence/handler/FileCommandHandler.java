package com.yapp.api.domain.file.persistence.handler;

import java.util.function.Consumer;

import com.yapp.api.domain.file.persistence.repository.FileRepository;

public interface FileCommandHandler {
	void save(Consumer<FileRepository> consumer);
}
