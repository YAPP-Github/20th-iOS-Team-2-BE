package com.yapp.api.domain.folder.persistence;

import com.yapp.core.entity.family.persistence.entity.Family;
import com.yapp.core.entity.folder.album.persistence.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface AlbumQuery {
    Optional<Album> findByDate(LocalDate date);

    List<Album> findByFamilyOrderByDateDesc(Family family);

    Page<Album> findAllByFamilyOrderByDateDesc(Pageable pageable, Family family);

    Optional<Album> findByFamilyAndId(Family family, Long albumId);
}
