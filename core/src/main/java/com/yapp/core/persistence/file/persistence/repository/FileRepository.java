package com.yapp.core.persistence.file.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.yapp.core.persistence.file.persistence.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.core.persistence.folder.album.persistence.entity.Album;
import com.yapp.core.persistence.family.persistence.entity.Family;

public interface FileRepository extends JpaRepository<File, Long> {
	List<File> findAllByFamily(Family family);

	List<File> findAllByFamilyAndKind(Family family, String kind);

	Page<File> findAllByFamilyAndKind(Family family, File.Kind kind, Pageable pageable);

	List<File> findAllByFamilyAndFavourite(Family family, Boolean value);

	Page<File> findAllByFamilyAndFavourite(Family family, Boolean value, Pageable pageable);

	Optional<File> findByFamilyAndId(Family family, Long fileId);

	Page<File> findAllByFamilyAndAlbum(Family family, Album album, Pageable pageable);
}
