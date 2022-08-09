package com.yapp.api.domain.folder.controller;

import com.yapp.api.domain.file.service.FileService;
import com.yapp.api.domain.folder.controller.model.AlbumRequest;
import com.yapp.api.domain.folder.controller.model.AlbumResponse;
import com.yapp.api.domain.folder.service.AlbumService;
import com.yapp.api.domain.folder.service.CommentService;
import com.yapp.api.global.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.api.global.security.auth.resolver.MustAuthenticated;
import com.yapp.core.persistence.user.entity.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<Void> uploadPhotos(@MustAuthenticated User user, @RequestBody AlbumRequest.Upload request) {
        albumService.uploadPhotos(user, request.getDate(), request.getLinks());

        return ResponseEntity.ok()
                .build();
    }

    @PostMapping(value = "/album/recordings", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> uploadRecordings(@MustAuthenticated User user, @RequestBody AlbumRequest.Upload request) {
        albumService.uploadRecordings(user, request.getDate(), request.getTitle(), request.getLinks()
                .get(0));
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping(value = "/album/favourite")
    ResponseEntity<AlbumResponse.Whether> doFavourite(
            @MustAuthenticated User user, @RequestParam(value = FILE_ID) Long fileId) {
        return ResponseEntity.ok(new AlbumResponse.Whether(fileService.makeFavourite(user, fileId)));

    }

    @PatchMapping(value = "/album/{albumId}", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> modifyAlbumName(
            @MustAuthenticated User user,
            @PathVariable(value = ALBUM_ID) Long albumId,
            @RequestBody AlbumRequest.Modify request) {
        albumService.modifyTitle(user, albumId, request.getAlbumName());
        return ResponseEntity.ok()
                .build();
    }

    @PatchMapping(value = "/album/{albumId}/date", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> modifyAlbumDate(
            @MustAuthenticated User user,
            @PathVariable(value = ALBUM_ID) Long albumId,
            @RequestBody AlbumRequest.ModifyDate request) {
        albumService.modifyDate(user, albumId, request.getDate());
        return ResponseEntity.ok()
                .build();
    }

    @Deprecated
    @DeleteMapping(value = _ALBUM_RESOURCE)
    ResponseEntity<Void> removeAlbum(@PathVariable(value = ALBUM_ID) Long albumId) {
        return null;
    }

    @PostMapping(value = "/album/{fileId}/comments", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<CommentResponse.Create> createComment(
            @MustAuthenticated User user,
            @PathVariable(value = FILE_ID) Long fileId,
            @RequestBody AlbumRequest.Comment request) {
        Long commentId = commentService.create(user, fileId, request.getContent());

        return ResponseEntity.ok(new CommentResponse.Create(request.getContent(), user.getId()));
    }

    @PatchMapping(value = "/album/comments/{commentId}", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> modifyComment(
            @MustAuthenticated User user,
            @PathVariable(value = COMMENT_ID) Long commentId,
            @RequestBody AlbumRequest.Comment request) {
        commentService.modify(user, commentId, request.getContent());

        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping(value = "/album/comments/{commentId}")
    ResponseEntity<Void> removeComment(@MustAuthenticated User user, @PathVariable(value = COMMENT_ID) Long commentId) {
        commentService.remove(user, commentId);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping(value = "/album/{albumId}/delegate")
    ResponseEntity<Void> delegate(
            @AuthenticationHasFamily User user,
            @PathVariable(value = "albumId") Long albumId,
            @RequestBody AlbumRequest.Delegate request) {
        albumService.delegate(user, albumId, request.getFileId());

        return ResponseEntity.ok()
                .build();
    }

    public static class CommentResponse {

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        @Setter
        public static class Create {
            private String content;
            private Long writerId;
        }
    }
}
