package com.yapp.allinone.domain.folder.controller;

import com.yapp.allinone.common.exception.ApiException;
import com.yapp.allinone.common.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.allinone.domain.common.response.PageResponse;
import com.yapp.allinone.domain.file.service.FileService;
import com.yapp.allinone.domain.folder.controller.model.AlbumResponse;
import com.yapp.allinone.domain.folder.service.AlbumService;
import com.yapp.allinone.domain.folder.service.CommentService;
import com.yapp.supporter.entity.file.persistence.entity.File;
import com.yapp.supporter.entity.folder.album.persistence.entity.Album;
import com.yapp.supporter.entity.user.entity.User;
import com.yapp.supporter.error.exception.ErrorCode;
import com.yapp.supporter.util.validator.ArgumentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static com.yapp.supporter.constant.ApiConstant.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class AlbumQueryApi {
    private static final String FAVOURITE = "favourite";
    private final ArgumentValidator argumentValidator;
    private final AlbumService albumService;
    private final CommentService commentService;
    private final FileService fileService;

    @GetMapping(value = "/album", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<?> retrieveAlbumList(
            @AuthenticationHasFamily User user,
            @RequestParam(value = TYPE, defaultValue = "kind") String type,
            @PageableDefault Pageable pageable) {

        if (argumentValidator.equal(KIND, type)) {
            return ResponseEntity.ok(AlbumResponse.KindElements.from(albumService.getList(user.getFamily())));
        }

        if (argumentValidator.equal(DATE, type)) {
            Page<Album> page = albumService.getList(user.getFamily(), pageable);

            return ResponseEntity.ok()
                    .body(PageResponse.of(new AlbumResponse.DateElements(page.getContent()
                            .stream()
                            .map(AlbumResponse.DateElements.AlbumInfo::of)
                            .collect(Collectors.toList())), page));
        }

        throw new ApiException(ErrorCode.ALBUM_RETRIEVE_TYPE_NOT_VALID);
    }

    @GetMapping(value = "/album/details/{albumId}", produces = APPLICATION_JSON_VALUE)
    PageResponse<AlbumResponse.DetailsAsDate> retrieveDetailsAsDate(
            @AuthenticationHasFamily User user,
            @PathVariable(value = ALBUM_ID) Long albumId,
            @PageableDefault Pageable pageable) {
        Album album = albumService.get(user.getFamily(), albumId);
        Page<File> files = fileService.getFiles(user.getFamily(), album, pageable);

        return PageResponse.of(AlbumResponse.DetailsAsDate.of(album, files.getContent()
                .stream()
                .map(AlbumResponse.AlbumDetail::from)
                .collect(Collectors.toList())), files);
    }

    @GetMapping(value = "/album/details", produces = APPLICATION_JSON_VALUE)
    PageResponse<AlbumResponse.DetailsAsKind> retrieveDetailsAsKind(
            @AuthenticationHasFamily User user,
            @RequestParam(value = KIND, defaultValue = FAVOURITE) String kind,
            @PageableDefault Pageable pageable) {

        Page<File> files = fileService.getFiles(user.getFamily(), kind.toUpperCase(), pageable);

        return PageResponse.of(AlbumResponse.DetailsAsKind.of(kind, files.getContent()
                .stream()
                .map(AlbumResponse.AlbumDetail::from)
                .collect(Collectors.toList())), files);
    }

    @GetMapping(value = "/album/{fileId}/comments", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<AlbumResponse.Comments> retrieveComments(
            @AuthenticationHasFamily User user,
            @PathVariable(value = FILE_ID) Long fileId) {
        return ResponseEntity.ok(AlbumResponse.Comments.from(commentService.getList(user, user.getFamily(), fileId)));
    }
}
