package com.yapp.api.domain.file.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.file.persistence.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {
	List<File> findAllByFamily(Family family);

	List<File> findAllByFamilyAndKind(Family family, String kind);

	List<File> findAllByFamilyAndFavourite(Family family, Boolean value);

	Optional<File> findByFamilyAndId(Family family, Long fileId);
}
