package com.yapp.api.domain.folder.service;

import com.yapp.api.domain.file.persistence.command.handler.FileCommandHandler;
import com.yapp.api.domain.file.persistence.query.handler.FileQueryHandler;
import com.yapp.api.domain.folder.persistence.command.handler.AlbumCommandHandler;
import com.yapp.api.domain.folder.persistence.query.handler.AlbumQueryHandler;
import com.yapp.api.global.error.exception.ApiException;
import com.yapp.core.constant.ServiceConstant;
import com.yapp.core.entity.family.persistence.entity.Family;
import com.yapp.core.entity.file.persistence.entity.File;
import com.yapp.core.entity.folder.album.persistence.entity.Album;
import com.yapp.core.entity.user.entity.User;
import com.yapp.core.error.exception.ErrorCode;
import com.yapp.core.error.exception.ExceptionThrowableLayer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.yapp.core.error.exception.ErrorCode.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlbumService implements ExceptionThrowableLayer {
    private static final int FIRST_INDEX = 0;

    private final AlbumCommandHandler albumCommandHandler;
    private final AlbumQueryHandler albumQueryHandler;
    private final FileCommandHandler fileCommandHandler;
    private final FileQueryHandler fileQueryHandler;

    public Album get(Family family, Long albumId) {
        return getAlbum(family, albumId);
    }

    public Map<String, List<File>> getList(Family family) {
        List<Album> albums = albumQueryHandler.findAll(family);

        Map<String, List<File>> filesByKindName = albums.stream()
                .flatMap(album -> album.getFiles()
                        .stream())
                .collect(Collectors.groupingBy(file -> file.getKind()
                        .getValue()));

        filesByKindName.put("favourite", albums.stream()
                .flatMap(album -> album.getFiles()
                        .stream())
                .filter(File::isFavourite)
                .collect(Collectors.toList()));

        return filesByKindName;
    }

    public Page<Album> getList(Family family, Pageable pageable) {
        return albumQueryHandler.findAll(family, pageable);
    }

    @Transactional
    public void uploadPhotos(User user, LocalDateTime dateTime, List<String> photos) {
        Album album = getAlbumOrCreate(user, dateTime);

        fileCommandHandler.saveAll(photos.stream()
                .map(link -> File.photoFile("-", link, album, dateTime, user.getFamily()))
                .collect(Collectors.toList()));

        if (album.noThumbnail()) {
            album.updateThumbnail(photos.get(FIRST_INDEX));
            albumCommandHandler.save(album);
        }
    }

    @Transactional
    public void uploadRecordings(User user, LocalDateTime dateTime, String title, String link) {
        Album album = getAlbumOrCreate(user, dateTime);

        if (Objects.isNull(title) || title.isBlank()) {
            title = dateTime.toLocalTime() + " 녹음";
        }

        fileCommandHandler.save(File.recordingFile(title, link, album, dateTime, user.getFamily()));

        if (album.noThumbnail()) {
            album.updateThumbnail(ServiceConstant.DEFAULT_IMAGE);
            albumCommandHandler.save(album);
        }
    }

    private Album getAlbumOrCreate(User user, LocalDateTime dateTime) {
        return albumQueryHandler.findOne(dateTime.toLocalDate())
                .orElseGet(() -> Album.builder()
                        .family(user.getFamily())
                        .date(dateTime.toLocalDate())
                        .build());
    }

    @Transactional
    public void modifyTitle(Family family, Long albumId, String toBe) {
        Album album = getAlbum(family, albumId);
        album.modifyTitle(toBe);
    }

    @Transactional
    public void modifyDate(Family family, Long albumId, LocalDateTime date) {
        Album album = getAlbum(family, albumId);
        album.modifyDate(date);

        List<File> files = album.getFiles();
        files.forEach(file -> file.modifyDate(date));
        fileCommandHandler.update(files);
    }

    @Transactional
    public void remove(Family family, Long albumId) {
        Album album = getAlbum(family, albumId);
        albumCommandHandler.remove(album);
    }

    @Transactional
    public void delegate(Family family, Long albumId, Long fileId) {
        Album album = getAlbum(family, albumId);
        File file = fileQueryHandler.findOne(family, fileId)
                .orElseThrow(() -> new ApiException(FILE_NOT_FOUND, packageName(this.getClass())));

        if (album.contains(file)) {
            if (file.isPhoto()) {
                album.updateThumbnail(file.getLink());
                return;
            }
            throw new ApiException(ErrorCode.RECORDING_CAN_NOT_BE_DELEGATOR, packageName(this.getClass()));
        }
        throw new ApiException(ALBUM_FILE_NOT_MATCH, packageName(this.getClass()));
    }

    @Transactional
    public void modifyFileDate(Family family, Long fileId, LocalDateTime dateTime) {
        File file = fileQueryHandler.findOne(family, fileId)
                .orElseThrow(() -> new ApiException(FILE_NOT_FOUND, packageName(this.getClass())));
        file.modifyDate(dateTime);

        Album album = albumQueryHandler.findOne(dateTime.toLocalDate())
                .orElseGet(() -> Album.builder()
                        .date(dateTime.toLocalDate())
                        .family(family)
                        .build());
        file.modifyAlbum(album);

        fileCommandHandler.save(file);
        albumCommandHandler.save(album);
    }

    private Album getAlbum(Family family, Long albumId) {
        return albumQueryHandler.findOne(family, albumId)
                .orElseThrow(() -> new ApiException(ALBUM_NOT_FOUND, packageName(this.getClass())));
    }
}
