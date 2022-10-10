package com.yapp.allinone.domain.folder.persistence.query.handler;

import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.folder.album.persistence.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface AlbumQueryHandler {
    Optional<Album> findOne(LocalDate date);

    Optional<Album> findOne(Family family, Long albumId);

    List<Album> findAll(Family family);

    Page<Album> findAll(Family family, Pageable pageable);
}
