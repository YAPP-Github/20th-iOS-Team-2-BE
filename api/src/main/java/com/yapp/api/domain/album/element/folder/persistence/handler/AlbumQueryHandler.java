package com.yapp.api.domain.album.element.folder.persistence.handler;

import java.time.LocalDate;
import java.util.Optional;

import com.yapp.api.domain.album.element.folder.persistence.entity.Album;

public interface AlbumQueryHandler {
	Optional<Album> findAlbumByDate(LocalDate date);
}
