package com.yapp.api.domain.file.persistence.handler;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;

import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.file.persistence.repository.FileRepository;

public interface FileQueryHandler {
	Optional<File> findOne(Function<FileRepository, Optional<File>> function);

	List<File> findList(Function<FileRepository, List<File>> function);

	Page<File> findPage(Function<FileRepository, Page<File>> function);
}
