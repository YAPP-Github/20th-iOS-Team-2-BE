package com.yapp.api.domain.album.element.folder.persistence.handler;

import java.util.function.Consumer;

import com.yapp.api.domain.album.element.folder.persistence.repository.FolderRepository;

public interface FolderCommandHandler {
	void saveFolder(Consumer<FolderRepository> consumer);
}
