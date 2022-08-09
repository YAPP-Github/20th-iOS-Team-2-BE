package com.yapp.core.persistence.file.persistence.handler;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;

import com.yapp.core.persistence.file.persistence.entity.File;
import com.yapp.core.persistence.file.persistence.repository.FileRepository;

public interface FileQueryHandler {
	Optional<File> findOne(Function<FileRepository, Optional<File>> function);

	List<File> findAll(Function<FileRepository, List<File>> function);

	Page<File> findAllAsPage(Function<FileRepository, Page<File>> function);
}
