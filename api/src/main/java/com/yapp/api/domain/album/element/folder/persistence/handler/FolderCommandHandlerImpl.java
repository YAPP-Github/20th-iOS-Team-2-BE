package com.yapp.api.domain.album.element.folder.persistence.handler;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.yapp.api.domain.album.element.folder.persistence.repository.FolderRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FolderCommandHandlerImpl implements FolderCommandHandler {
	private final FolderRepository folderRepository;

	@Override
	public void saveFolder(Consumer<FolderRepository> consumer) {
		consumer.accept(folderRepository);
	}
}
