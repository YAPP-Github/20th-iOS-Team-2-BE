package com.yapp.api.domain.file.persistence.repository;

import com.yapp.supporter.constant.FileKind;
import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.file.persistence.entity.File;
import com.yapp.supporter.entity.folder.album.persistence.entity.Album;
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

    Page<File> findAllByFamilyAndAlbumOrderByPriorityDescDateTimeDesc(Family family, Album album, Pageable pageable);

    List<File> findAllByFamilyAndLink(Family family, String thumbnail);
}
