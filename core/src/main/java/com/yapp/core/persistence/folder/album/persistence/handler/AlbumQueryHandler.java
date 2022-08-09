package com.yapp.core.persistence.folder.album.persistence.handler;

import com.yapp.core.persistence.folder.album.persistence.entity.Album;
import com.yapp.core.persistence.folder.album.persistence.repository.AlbumRepository;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface AlbumQueryHandler {
    List<Album> findAll(Function<AlbumRepository, List<Album>> function);

    Page<Album> findAllAsPage(Function<AlbumRepository, Page<Album>> function);

    Optional<Album> findOne(Function<AlbumRepository, Optional<Album>> function);
}
