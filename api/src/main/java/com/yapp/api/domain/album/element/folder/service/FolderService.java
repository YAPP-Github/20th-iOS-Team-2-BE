package com.yapp.api.domain.album.element.folder.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.api.domain.album.element.folder.persistence.handler.FolderCommandHandler;
import com.yapp.api.domain.album.element.folder.persistence.handler.FolderQueryHandler;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FolderService {
	private final FolderCommandHandler folderCommandHandler;
	private final FolderQueryHandler folderQueryHandler;
}
