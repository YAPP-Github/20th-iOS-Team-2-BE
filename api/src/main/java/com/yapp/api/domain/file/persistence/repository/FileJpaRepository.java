package com.yapp.api.domain.file.persistence.repository;

import com.yapp.realtime.constant.FileKind;
import com.yapp.realtime.entity.family.persistence.entity.Family;
import com.yapp.realtime.entity.file.persistence.entity.File;
import com.yapp.realtime.entity.folder.album.persistence.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/14
 * Info :
 **/
public interface FileJpaRepository extends JpaRepository<File, Long> {
    Optional<File> findByIdAndFamily(Long fileId, Family family);

    List<File> findAllByFamily(Family family);

    Page<File> findAllByFamilyAndKind(Family family, FileKind kind, Pageable pageable);

    Page<File> findAllByFamilyAndFavourite(Family family, Boolean favourite, Pageable pageable);

    Page<File> findAllByFamilyAndAlbum(Family family, Album album, Pageable pageable);
}
