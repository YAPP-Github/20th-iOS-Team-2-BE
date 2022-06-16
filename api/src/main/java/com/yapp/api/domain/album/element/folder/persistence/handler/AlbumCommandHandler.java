package com.yapp.api.domain.album.element.folder.persistence.handler;

import java.util.function.Consumer;

import com.yapp.api.domain.album.element.folder.persistence.repository.AlbumRepository;

public interface AlbumCommandHandler {
	void saveAlbum(Consumer<AlbumRepository> consumer);
}
