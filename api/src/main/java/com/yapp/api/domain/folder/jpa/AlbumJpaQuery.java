package com.yapp.api.domain.folder.jpa;

import com.yapp.core.persistence.family.persistence.entity.Family;
import com.yapp.core.persistence.folder.album.persistence.entity.Album;
import com.yapp.core.persistence.folder.album.persistence.repository.AlbumQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface AlbumJpaQuery extends JpaRepository<Album, Long>, AlbumQuery {
    Optional<Album> findByDate(LocalDate date);

    List<Album> findByFamilyOrderByDateDesc(Family family);

    Page<Album> findAllByFamilyOrderByDateDesc(Pageable pageable, Family family);

    Optional<Album> findByFamilyAndId(Family family, Long albumId);
}
