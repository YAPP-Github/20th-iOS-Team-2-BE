package com.yapp.api.domain.folder.controller;

import com.yapp.api.domain.file.service.FileService;
import com.yapp.api.domain.folder.controller.model.AlbumRequest;
import com.yapp.api.domain.folder.controller.model.AlbumResponse;
import com.yapp.api.domain.folder.controller.model.CommentResponse;
import com.yapp.api.domain.folder.service.AlbumService;
import com.yapp.api.domain.folder.service.CommentService;
import com.yapp.api.global.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.core.entity.user.entity.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.yapp.core.constant.ApiConstant.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AlbumCommandApi {
    private final AlbumService albumService;
    private final FileService fileService;
    private final CommentService commentService;

    @PostMapping(value = "/album/photos", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> uploadPhotos(
            @AuthenticationHasFamily User user, @Valid @RequestBody AlbumRequest.Upload request) {
        albumService.uploadPhotos(user, request.getDate(), request.getLinks());

        return ResponseEntity.ok()
                .build();
    }

    @PostMapping(value = "/album/recordings", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> uploadRecordings(
            @AuthenticationHasFamily User user, @Valid @RequestBody AlbumRequest.Upload request) {
        albumService.uploadRecordings(user, request.getDate(), request.getTitle(), request.getLinks()
                .get(0));

        return ResponseEntity.ok()
                .build();
    }

    @PostMapping(value = "/album/favourite")
    ResponseEntity<AlbumResponse.Whether> doFavourite(
            @AuthenticationHasFamily User user, @RequestParam(value = FILE_ID) Long fileId) {
        return ResponseEntity.ok(new AlbumResponse.Whether(fileService.makeFavourite(user.getFamily(), fileId)));
    }

    @PatchMapping(value = "/album/{albumId}", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> modifyAlbumName(
            @AuthenticationHasFamily User user,
            @PathVariable(value = ALBUM_ID) Long albumId,
            @Valid @RequestBody AlbumRequest.Modify request) {
        albumService.modifyTitle(user.getFamily(), albumId, request.getAlbumName());

        return ResponseEntity.ok()
                .build();
    }

    @PatchMapping(value = "/album/{albumId}/date", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> modifyAlbumDate(
            @AuthenticationHasFamily User user,
            @PathVariable(value = ALBUM_ID) Long albumId,
            @Valid @RequestBody AlbumRequest.ModifyDate request) {
        albumService.modifyDate(user.getFamily(), albumId, request.getDate());

        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping(value = "/album/{albumId}")
    ResponseEntity<Void> removeAlbum(
            @AuthenticationHasFamily User user, @PathVariable(value = ALBUM_ID) Long albumId) {
        albumService.remove(user.getFamily(), albumId);

        return ResponseEntity.ok()
                .build();
    }

    @PostMapping(value = "/album/{fileId}/comments", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<CommentResponse.Create> createComment(
            @AuthenticationHasFamily User user,
            @PathVariable(value = FILE_ID) Long fileId,
            @Valid @RequestBody AlbumRequest.Comment request) {
        commentService.create(user, user.getFamily(), fileId, request.getContent());

        return ResponseEntity.ok(new CommentResponse.Create(request.getContent()));
    }

    @PatchMapping(value = "/album/comments/{commentId}", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> modifyComment(
            @AuthenticationHasFamily User user,
            @PathVariable(value = COMMENT_ID) Long commentId,
            @Valid @RequestBody AlbumRequest.Comment request) {
        commentService.modify(commentId, request.getContent());

        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping(value = "/album/comments/{commentId}")
    ResponseEntity<Void> removeComment(
            @AuthenticationHasFamily User user,
            @PathVariable(value = COMMENT_ID) Long commentId) {
        commentService.remove(commentId);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping(value = "/album/{albumId}/delegate")
    ResponseEntity<Void> delegate(
            @AuthenticationHasFamily User user,
            @PathVariable(value = "albumId") Long albumId,
            @Valid @RequestBody AlbumRequest.Delegate request) {
        albumService.delegate(user.getFamily(), albumId, request.getFileId());

        return ResponseEntity.ok()
                .build();
    }
}
