package com.yapp.api.domain.album.element.folder.persistence.handler;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.yapp.api.domain.album.element.folder.persistence.entity.Album;
import com.yapp.api.domain.album.element.folder.persistence.repository.AlbumRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AlbumQueryHandlerImpl implements AlbumQueryHandler {
	private final AlbumRepository albumRepository;

	@Override
	public Optional<Album> findAlbumByDate(LocalDate date) {
		return albumRepository.findByDate(date);
	}

	@Override
	public List<Album> findAll(Function<AlbumRepository, List<Album>> function) {
		return function.apply(albumRepository);
	}

	@Override
	public Integer countByKind(Function<AlbumRepository, Integer> function) {
		return function.apply(albumRepository);
	}

	@Override
	public Optional<Album> findAlbum(Function<AlbumRepository, Optional<Album>> function) {
		return function.apply(albumRepository);
	}
}
