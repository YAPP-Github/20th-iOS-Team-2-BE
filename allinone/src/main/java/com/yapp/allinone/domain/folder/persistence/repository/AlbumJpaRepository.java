package com.yapp.allinone.domain.folder.persistence.repository;

import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.folder.album.persistence.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/14
 * Info :
 **/
public interface AlbumJpaRepository extends JpaRepository<Album, Long> {
    Optional<Album> findByDate(LocalDate date);

    Optional<Album> findByFamilyAndId(Family family, Long albumId);

    List<Album> findAllByFamily(Family family);

    Page<Album> findAllByFamilyOrderByDateDesc(Family family, Pageable pageable);
}
