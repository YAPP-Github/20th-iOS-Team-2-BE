package com.yapp.api.domain.file.persistence.query.handler;

import com.yapp.api.domain.file.persistence.repository.FileJpaRepository;
import com.yapp.realtime.constant.FileKind;
import com.yapp.realtime.entity.family.persistence.entity.Family;
import com.yapp.realtime.entity.file.persistence.entity.File;
import com.yapp.realtime.entity.folder.album.persistence.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class FileQueryJpaHandler implements FileQueryHandler {
    private final FileJpaRepository fileJpaRepository;

    public FileQueryJpaHandler(FileJpaRepository fileJpaRepository) {
        this.fileJpaRepository = fileJpaRepository;
    }

    @Override
    public Optional<File> findOne(Family family, Long fileId) {
        return fileJpaRepository.findByIdAndFamily(fileId, family);
    }

    @Override
    public List<File> findAll(Family family) {
        return fileJpaRepository.findAllByFamily(family);
    }

    @Override
    public Page<File> findAll(Family family, FileKind kind, Pageable pageable) {
        return fileJpaRepository.findAllByFamilyAndKind(family, kind, pageable);
    }

    @Override
    public Page<File> findAll(Family family, Boolean favourite, Pageable pageable) {
        return fileJpaRepository.findAllByFamilyAndFavourite(family, favourite, pageable);
    }

    @Override
    public Page<File> findAll(Family family, Album album, Pageable pageable) {
        return fileJpaRepository.findAllByFamilyAndAlbum(family, album, pageable);
    }
}
