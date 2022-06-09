package com.yapp.api.domain.file.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.api.domain.file.persistence.handler.FileCommandHandler;
import com.yapp.api.domain.file.persistence.handler.FileQueryHandler;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileService {
	private final FileCommandHandler fileCommandHandler;
	private final FileQueryHandler fileQueryHandler;
}
