package com.yapp.core.persistence.folder.album.persistence.handler;

import com.yapp.core.persistence.folder.album.persistence.repository.AlbumRepository;

import java.util.function.Consumer;

public interface AlbumCommandHandler {
    void create(Consumer<AlbumRepository> consumer);

    void remove(Consumer<AlbumRepository> consumer);
}
