package com.yapp.api.domain.file.persistence.handler;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.file.persistence.repository.FileRepository;

public interface FileCommandHandler {
	void save(Consumer<FileRepository> consumer);

	List<File> findBy(Function<FileRepository, List<File>> function);
}
