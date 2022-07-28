package com.yapp.api.domain.album.controller;

import static com.yapp.core.constant.ApiConstant.*;
import static org.springframework.http.MediaType.*;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.api.domain.album.controller.dto.AlbumRequest;
import com.yapp.api.domain.album.controller.dto.AlbumResponse;
import com.yapp.api.domain.album.element.comment.service.CommentService;
import com.yapp.api.domain.album.element.folder.service.AlbumService;
import com.yapp.api.domain.file.service.FileService;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.api.global.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.api.global.security.auth.resolver.MustAuthenticated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AlbumCommandApi {
	private final AlbumService albumService;
	private final FileService fileService;
	private final CommentService commentService;

	// async
	@PostMapping(value = _ALBUM_PHOTOS, consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> uploadPhotos(@MustAuthenticated User user, @RequestBody AlbumRequest.Upload request) {
		CompletableFuture.runAsync(() -> albumService.uploadPhotos(user, request.getDate(), request.getLinks()))
						 .exceptionally(throwable -> {
							 log.error("[ERROR] {}" + throwable.getMessage());
							 return null;
						 });
		return ResponseEntity.ok()
							 .build();
	}

	// async
	@PostMapping(value = _ALBUM_RECORDINGS, consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> uploadRecordings(@MustAuthenticated User user, @RequestBody AlbumRequest.Upload request) {
		CompletableFuture.runAsync(() -> albumService.uploadRecordings(user,
																	   request.getDate(),
																	   request.getTitle(),
																	   request.getLinks()
																			  .get(0)))
						 .exceptionally(throwable -> {
							 log.error("[ERROR] {}", throwable.getMessage());
							 return null;
						 });
		return ResponseEntity.ok()
							 .build();
	}

	// sync, cause display status for Client
	@PostMapping(value = _ALBUM_FAVOURITE)
	ResponseEntity<AlbumResponse.Whether> doFavourite(@MustAuthenticated User user,
													  @RequestParam(value = FILE_ID) Long fileId) {
		return ResponseEntity.ok(new AlbumResponse.Whether(fileService.makeFavourite(user, fileId)));

	}

	@PatchMapping(value = _ALBUM_RESOURCE, consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modifyAlbumName(@MustAuthenticated User user,
										 @PathVariable(value = ALBUM_ID) Long albumId,
										 @RequestBody AlbumRequest.Modify request) {
		CompletableFuture.runAsync(() -> albumService.modifyTitle(user, albumId, request.getAlbumName()))
						 .exceptionally(throwable -> {
							 log.error("[ERROR] {}", throwable.getMessage());
							 return null;
						 });
		return ResponseEntity.ok()
							 .build();
	}

	@PatchMapping(value = "/album/{albumId}/date", consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modifyAlbumDate(@MustAuthenticated User user,
										 @PathVariable(value = ALBUM_ID) Long albumId,
										 @RequestBody AlbumRequest.ModifyDate request) {
		CompletableFuture.runAsync(() -> albumService.modifyDate(user, albumId, request.getDate()))
						 .exceptionally(throwable -> {
							 log.error("[ERROR] {}", throwable.getMessage());
							 return null;
						 });
		return ResponseEntity.ok()
							 .build();
	}

	@DeleteMapping(value = _ALBUM_RESOURCE)
	ResponseEntity<Void> removeAlbum(@PathVariable(value = ALBUM_ID) Long albumId) {
		return null;
	}

	@PostMapping(value = _ALBUM_COMMENTS, consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<CommentResponse.Create> createComment(@MustAuthenticated User user,
														 @PathVariable(value = FILE_ID) Long fileId,
														 @RequestBody AlbumRequest.Comment request) {
		Long commentId = commentService.create(user, fileId, request.getContent());

		return ResponseEntity.ok(new CommentResponse.Create(request.getContent(), user.getId()));
	}

	@PatchMapping(value = _ALBUM_COMMENT_RESOURCE, consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modifyComment(@MustAuthenticated User user,
									   @PathVariable(value = COMMENT_ID) Long commentId,
									   @RequestBody AlbumRequest.Comment request) {
		CompletableFuture.runAsync(() -> commentService.modify(user, commentId, request.getContent()))
						 .exceptionally(throwable -> {
							 log.error("[ERROR] {}", throwable.getMessage());
							 return null;
						 });

		return ResponseEntity.ok()
							 .build();
	}

	@DeleteMapping(value = _ALBUM_COMMENT_RESOURCE)
	ResponseEntity<Void> removeComment(@MustAuthenticated User user, @PathVariable(value = COMMENT_ID) Long commentId) {
		CompletableFuture.runAsync(() -> commentService.remove(user, commentId))
						 .exceptionally(throwable -> {
							 log.error("[ERROR] {}", throwable.getMessage());
							 return null;
						 });

		return ResponseEntity.ok()
							 .build();
	}

	@PutMapping(value = "/album/{albumId}/delegate")
	ResponseEntity<Void> delegate(@AuthenticationHasFamily User user,
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
