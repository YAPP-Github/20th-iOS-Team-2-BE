package com.yapp.core.persistence.folder.album.persistence.repository;

import com.yapp.core.persistence.folder.album.persistence.entity.Album;
import com.yapp.core.persistence.family.persistence.entity.Family;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Optional<Album> findByDate(LocalDate date);

    List<Album> findByFamilyOrderByDateDesc(Family family);

    Page<Album> findAllByFamilyOrderByDateDesc(Pageable pageable, Family family);

    Optional<Album> findByFamilyAndId(Family family, Long albumId);
}
