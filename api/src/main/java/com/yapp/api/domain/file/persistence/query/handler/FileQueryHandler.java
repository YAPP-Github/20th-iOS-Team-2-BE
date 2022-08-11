package com.yapp.api.domain.file.persistence.query.handler;

import com.yapp.core.constant.FileKind;
import com.yapp.core.entity.family.persistence.entity.Family;
import com.yapp.core.entity.file.persistence.entity.File;
import com.yapp.core.entity.folder.album.persistence.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface FileQueryHandler {
    Optional<File> findOne(Family family, Long fileId);

    List<File> findAll(Family family);

    List<File> findAll(Family family, String kind);

    Page<File> findAll(Family family, FileKind kind, Pageable pageable);

    List<File> findAll(Family family, Boolean value);

    Page<File> findAll(Family family, Boolean value, Pageable pageable);


    Page<File> findAll(Family family, Album album, Pageable pageable);
}
