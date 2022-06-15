package com.yapp.api.domain.album.element.folder.persistence.handler;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.yapp.api.domain.album.element.folder.persistence.entity.Folder;
import com.yapp.api.domain.album.element.folder.persistence.repository.FolderRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FolderQueryHandlerImpl implements FolderQueryHandler {
	private final FolderRepository folderRepository;

	@Override
	public Optional<Folder> findFolderByDate(LocalDate date) {
		return folderRepository.findByDate(date);
	}
}
