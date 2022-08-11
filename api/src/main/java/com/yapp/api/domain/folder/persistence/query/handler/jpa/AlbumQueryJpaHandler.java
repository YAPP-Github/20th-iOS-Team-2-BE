package com.yapp.api.domain.folder.persistence.query.handler.jpa;

import com.yapp.api.domain.folder.persistence.query.handler.AlbumQueryHandler;
import com.yapp.api.domain.folder.persistence.query.repository.AlbumQuery;
import com.yapp.core.entity.family.persistence.entity.Family;
import com.yapp.core.entity.folder.album.persistence.entity.Album;
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
    private final AlbumQuery albumQuery;

    public AlbumQueryJpaHandler(AlbumQuery albumQuery) {
        this.albumQuery = albumQuery;
    }

    @Override
    public Optional<Album> findOne(LocalDate date) {
        return Optional.empty();
    }

    @Override
    public Optional<Album> findOne(
            Family family, Long albumId) {
        return Optional.empty();
    }

    @Override
    public List<Album> findAll(Family family) {
        return null;
    }

    @Override
    public Page<Album> findAll(
            Family family, Pageable pageable) {
        return null;
    }
}
