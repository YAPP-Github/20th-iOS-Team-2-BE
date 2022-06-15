package com.yapp.api.domain.file.persistence.handler;

import org.springframework.stereotype.Component;

import com.yapp.api.domain.file.persistence.repository.FileRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FileCommandHandlerImpl implements FileCommandHandler {
	private final FileRepository fileRepository;
}
