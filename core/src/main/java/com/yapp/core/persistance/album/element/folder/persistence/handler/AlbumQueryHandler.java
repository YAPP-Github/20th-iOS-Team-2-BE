package com.yapp.core.persistance.album.element.folder.persistence.handler;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;

import com.yapp.core.persistance.album.element.folder.persistence.entity.Album;
import com.yapp.core.persistance.album.element.folder.persistence.repository.AlbumRepository;

public interface AlbumQueryHandler {
	List<Album> findAll(Function<AlbumRepository, List<Album>> function);

	Page<Album> findAllAsPage(Function<AlbumRepository, Page<Album>> function);

	Integer countByKind(Function<AlbumRepository, Integer> function);

	Optional<Album> findAlbum(Function<AlbumRepository, Optional<Album>> function);

	Optional<Album> findAlbumByDate(LocalDate toLocalDate);
}
