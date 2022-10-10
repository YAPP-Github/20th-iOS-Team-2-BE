package com.yapp.allinone.domain.file.service;

import com.yapp.allinone.common.exception.ApiException;
import com.yapp.allinone.domain.file.persistence.command.handler.FileCommandHandler;
import com.yapp.allinone.domain.file.persistence.query.handler.FileQueryHandler;
import com.yapp.supporter.constant.FileKind;
import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.file.persistence.entity.File;
import com.yapp.supporter.entity.folder.album.persistence.entity.Album;
import com.yapp.supporter.error.exception.ExceptionThrowableLayer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.yapp.supporter.error.exception.ErrorCode.FILE_NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileService implements ExceptionThrowableLayer {

    private final FileCommandHandler fileCommandHandler;
    private final FileQueryHandler fileQueryHandler;

    public Page<File> getFiles(Family family, Album album, Pageable pageable) {
        return fileQueryHandler.findAll(family, album, pageable);
    }

    public Page<File> getFiles(Family family, String kind, Pageable pageable) {
        if (kind.equalsIgnoreCase("favourite")) {
            return fileQueryHandler.findAll(family, true, pageable);
        }
        return fileQueryHandler.findAll(family, FileKind.valueOf(kind), pageable);
    }

    @Transactional
    public void remove(Family family, Long fileId) {
        File file = fileQueryHandler.findOne(family, fileId)
                .orElseThrow(() -> new ApiException(FILE_NOT_FOUND, packageName(this.getClass())));

        fileCommandHandler.remove(file);
    }

    @Transactional
    public boolean makeFavourite(Family family, Long fileId) {
        return fileQueryHandler.findOne(family, fileId)
                .orElseThrow(() -> new ApiException(FILE_NOT_FOUND, packageName(this.getClass())))
                .doFavour();
    }
}
