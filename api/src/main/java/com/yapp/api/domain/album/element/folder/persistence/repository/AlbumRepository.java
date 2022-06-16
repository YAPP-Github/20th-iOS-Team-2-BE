package com.yapp.api.domain.album.element.folder.persistence.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.api.domain.album.element.folder.persistence.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
	Optional<Album> findByDate(LocalDate date);
}
