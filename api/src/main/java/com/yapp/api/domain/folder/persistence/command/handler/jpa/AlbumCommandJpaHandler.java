package com.yapp.api.domain.folder.persistence.command.handler.jpa;

import com.yapp.api.domain.folder.persistence.command.handler.AlbumCommandHandler;
import com.yapp.api.domain.folder.persistence.repository.AlbumJpaRepository;
import com.yapp.realtime.entity.folder.album.persistence.entity.Album;
import org.springframework.stereotype.Component;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class AlbumCommandJpaHandler implements AlbumCommandHandler {
    private final AlbumJpaRepository albumJpaRepository;

    public AlbumCommandJpaHandler(AlbumJpaRepository albumJpaRepository) {
        this.albumJpaRepository = albumJpaRepository;
    }

    @Override
    public void save(Album album) {
        albumJpaRepository.save(album);
    }

    @Override
    public void remove(Album album) {
        albumJpaRepository.delete(album);
    }
}
