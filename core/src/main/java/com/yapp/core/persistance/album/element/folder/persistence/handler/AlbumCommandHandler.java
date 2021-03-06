package com.yapp.core.persistance.album.element.folder.persistence.handler;

import java.util.function.Consumer;

import com.yapp.core.persistance.album.element.folder.persistence.repository.AlbumRepository;

public interface AlbumCommandHandler {
	void saveAlbum(Consumer<AlbumRepository> consumer);

	void removeOne(Consumer<AlbumRepository> consumer);
}
