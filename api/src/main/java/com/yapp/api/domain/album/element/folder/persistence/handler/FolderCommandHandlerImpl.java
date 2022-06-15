package com.yapp.api.domain.album.element.folder.persistence.handler;

import org.springframework.stereotype.Component;

import com.yapp.api.domain.album.element.folder.persistence.repository.FolderRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FolderCommandHandlerImpl implements FolderCommandHandler {
	private final FolderRepository folderRepository;
}
