package com.yapp.core.persistance.album.element.folder.persistence.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.core.persistance.album.element.folder.persistence.entity.Album;
import com.yapp.core.persistance.family.persistence.entity.Family;

public interface AlbumRepository extends JpaRepository<Album, Long> {
	Optional<Album> findByDate(LocalDate date);

	List<Album> findByFamilyOrderByDateDesc(Family family);

	Page<Album> findAllByFamilyOrderByDateDesc(Pageable pageable, Family family);

	Optional<Album> findByFamilyAndId(Family family, Long albumId);
}
