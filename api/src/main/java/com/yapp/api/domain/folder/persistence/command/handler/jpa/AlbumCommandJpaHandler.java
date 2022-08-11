package com.yapp.api.domain.folder.persistence.command.handler.jpa;

import com.yapp.api.domain.folder.persistence.command.handler.AlbumCommandHandler;
import com.yapp.api.domain.folder.persistence.command.repository.AlbumCommand;
import org.springframework.stereotype.Component;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class AlbumCommandJpaHandler implements AlbumCommandHandler {
    private final AlbumCommand albumCommand;

    public AlbumCommandJpaHandler(AlbumCommand albumCommand) {
        this.albumCommand = albumCommand;
    }
}
