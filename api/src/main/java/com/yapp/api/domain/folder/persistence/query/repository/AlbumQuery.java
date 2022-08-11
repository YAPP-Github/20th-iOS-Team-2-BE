package com.yapp.api.domain.folder.persistence.query.repository;

import com.yapp.core.entity.folder.album.persistence.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public class AlbumQuery extends JpaRepository<Album, Long> {
}
