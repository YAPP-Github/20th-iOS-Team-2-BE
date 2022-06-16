package com.yapp.api.domain.album.element.folder.persistence.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.api.domain.album.element.folder.persistence.entity.Album;
import com.yapp.api.domain.family.persistence.entity.Family;

public interface AlbumRepository extends JpaRepository<Album, Long> {
	Optional<Album> findByDate(LocalDate date);

	List<Album> findByFamily(Family family);

	Optional<Album> findByFamilyAndId(Family family, Long albumId);
}
