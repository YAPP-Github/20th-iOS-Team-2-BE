package com.yapp.api.domain.file.persistence.query.handler;

import com.yapp.api.domain.file.persistence.query.repository.FileQuery;
import com.yapp.core.constant.FileKind;
import com.yapp.core.entity.family.persistence.entity.Family;
import com.yapp.core.entity.file.persistence.entity.File;
import com.yapp.core.entity.folder.album.persistence.entity.Album;
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
    private final FileQuery fileQuery;

    public FileQueryJpaHandler(FileQuery fileQuery) {
        this.fileQuery = fileQuery;
    }

    @Override
    public Optional<File> findOne(
            Family family, Long fileId) {
        return Optional.empty();
    }

    @Override
    public List<File> findAll(Family family) {
        return null;
    }

    @Override
    public List<File> findAll(
            Family family, String kind) {
        return null;
    }

    @Override
    public Page<File> findAll(
            Family family, FileKind kind, Pageable pageable) {
        return null;
    }

    @Override
    public List<File> findAll(
            Family family, Boolean value) {
        return null;
    }

    @Override
    public Page<File> findAll(
            Family family, Boolean value, Pageable pageable) {
        return null;
    }

    @Override
    public Page<File> findAll(
            Family family, Album album, Pageable pageable) {
        return null;
    }
}
