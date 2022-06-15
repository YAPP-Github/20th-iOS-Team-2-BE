package com.yapp.api.domain.album.element.folder.persistence.handler;

import java.time.LocalDate;
import java.util.Optional;

import com.yapp.api.domain.album.element.folder.persistence.entity.Folder;

public interface FolderQueryHandler {
	Optional<Folder> findFolderByDate(LocalDate date);
}
