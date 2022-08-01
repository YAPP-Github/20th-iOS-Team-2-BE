package com.yapp.core.persistance.album.element.folder.persistence.handler;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.yapp.core.persistance.album.element.folder.persistence.repository.AlbumRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AlbumCommandHandlerImpl implements AlbumCommandHandler {
	private final AlbumRepository albumRepository;

	@Override
	public void saveAlbum(Consumer<AlbumRepository> consumer) {
		consumer.accept(albumRepository);
	}

	@Override
	public void removeOne(Consumer<AlbumRepository> consumer) {
		consumer.accept(albumRepository);
	}
}
