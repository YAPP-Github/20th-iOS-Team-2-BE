package com.yapp.allinone.domain.folder.persistence.query.handler.jpa;

import com.yapp.allinone.domain.folder.persistence.query.handler.AlbumQueryHandler;
import com.yapp.allinone.domain.folder.persistence.repository.AlbumJpaRepository;
import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.folder.album.persistence.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class AlbumQueryJpaHandler implements AlbumQueryHandler {
    private final AlbumJpaRepository albumJpaRepository;

    public AlbumQueryJpaHandler(AlbumJpaRepository albumJpaRepository) {
        this.albumJpaRepository = albumJpaRepository;
    }

    @Override
    public Optional<Album> findOne(LocalDate date) {
        return albumJpaRepository.findByDate(date);
    }

    @Override
    public Optional<Album> findOne(Family family, Long albumId) {
        return albumJpaRepository.findByFamilyAndId(family, albumId);
    }

    @Override
    public List<Album> findAll(Family family) {
        return albumJpaRepository.findAllByFamily(family);
    }

    @Override
    public Page<Album> findAll(Family family, Pageable pageable) {
        return albumJpaRepository.findAllByFamilyOrderByDateDesc(family, pageable);
    }
}
