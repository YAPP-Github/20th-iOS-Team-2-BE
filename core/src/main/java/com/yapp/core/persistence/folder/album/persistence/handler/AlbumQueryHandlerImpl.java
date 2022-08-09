package com.yapp.core.persistence.folder.album.persistence.handler;

import com.yapp.core.persistence.folder.album.persistence.entity.Album;
import com.yapp.core.persistence.folder.album.persistence.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class AlbumQueryHandlerImpl implements AlbumQueryHandler {
    private final AlbumRepository albumRepository;

    @Override
    public Page<Album> findAllAsPage(Function<AlbumRepository, Page<Album>> function) {
        return function.apply(albumRepository);
    }

    @Override
    public List<Album> findAll(Function<AlbumRepository, List<Album>> function) {
        return function.apply(albumRepository);
    }

    @Override
    public Optional<Album> findOne(Function<AlbumRepository, Optional<Album>> function) {
        return function.apply(albumRepository);
    }
}
