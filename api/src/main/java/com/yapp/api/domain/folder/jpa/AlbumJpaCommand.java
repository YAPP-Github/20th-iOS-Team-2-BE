package com.yapp.api.domain.folder.jpa;

import com.yapp.core.persistence.folder.album.persistence.entity.Album;
import com.yapp.core.persistence.folder.album.persistence.repository.AlbumCommand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface AlbumJpaCommand extends JpaRepository<Album, Long>, AlbumCommand {
}
