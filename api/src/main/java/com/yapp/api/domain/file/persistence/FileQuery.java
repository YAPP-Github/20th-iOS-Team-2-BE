package com.yapp.api.domain.file.persistence;

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
 * Date : 2022/08/10
 * Info :
 **/
public interface FileQuery {
    List<File> findAllByFamily(Family family);

    List<File> findAllByFamilyAndKind(Family family, String kind);

    Page<File> findAllByFamilyAndKind(Family family, FileKind kind, Pageable pageable);

    List<File> findAllByFamilyAndFavourite(Family family, Boolean value);

    Page<File> findAllByFamilyAndFavourite(Family family, Boolean value, Pageable pageable);

    Optional<File> findByFamilyAndId(Family family, Long fileId);

    Page<File> findAllByFamilyAndAlbum(Family family, Album album, Pageable pageable);
}
