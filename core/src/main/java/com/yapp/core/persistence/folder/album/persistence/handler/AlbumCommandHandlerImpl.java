package com.yapp.core.persistence.folder.album.persistence.handler;

import com.yapp.core.persistence.folder.album.persistence.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class AlbumCommandHandlerImpl implements AlbumCommandHandler {
    private final AlbumRepository albumRepository;

    @Override
    public void create(Consumer<AlbumRepository> consumer) {
        consumer.accept(albumRepository);
    }

    @Override
    public void remove(Consumer<AlbumRepository> consumer) {
        consumer.accept(albumRepository);
    }
}
