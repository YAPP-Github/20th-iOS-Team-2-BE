package com.yapp.api.domain.folder.persistence.command.handler;

import com.yapp.supporter.entity.folder.album.persistence.entity.Album;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface AlbumCommandHandler {
    void save(Album album);

    void remove(Album album);
}
