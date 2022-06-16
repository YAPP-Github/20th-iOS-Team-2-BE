package com.yapp.api.domain.album.element.folder.persistence.handler;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.yapp.api.domain.album.element.folder.persistence.entity.Album;
import com.yapp.api.domain.album.element.folder.persistence.repository.AlbumRepository;

public interface AlbumQueryHandler {
	Optional<Album> findAlbumByDate(LocalDate date);

	List<Album> findAll(Function<AlbumRepository, List<Album>> function);

	Integer countByKind(Function<AlbumRepository, Integer> function);

	Optional<Album> findAlbum(Function<AlbumRepository, Optional<Album>> function);
}
