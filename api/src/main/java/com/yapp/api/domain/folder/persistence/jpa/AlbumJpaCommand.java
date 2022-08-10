package com.yapp.api.domain.folder.persistence.jpa;

import com.yapp.api.domain.folder.persistence.AlbumCommand;
import com.yapp.core.entity.folder.album.persistence.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface AlbumJpaCommand extends JpaRepository<Album, Long>, AlbumCommand {
}
