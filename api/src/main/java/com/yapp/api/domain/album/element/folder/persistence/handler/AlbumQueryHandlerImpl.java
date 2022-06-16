package com.yapp.api.domain.album.element.folder.persistence.handler;

import java.time.LocalDate;
import java.util.Optional;

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
}
