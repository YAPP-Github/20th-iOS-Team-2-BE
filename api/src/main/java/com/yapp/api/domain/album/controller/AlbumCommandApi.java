package com.yapp.api.domain.album.controller;

import static com.yapp.core.constant.ApiConstant.*;
import static org.springframework.http.MediaType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.api.domain.album.controller.dto.AlbumRequest;
import com.yapp.api.domain.album.controller.dto.AlbumResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AlbumCommandApi {
	@PostMapping(value = _ALBUM_PHOTOS, consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> uploadPhotos(@RequestBody AlbumRequest.Upload request) {
		return null;
	}

	@PostMapping(value = _ALBUM_RECORDINGS, consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> uploadRecordings(@RequestBody AlbumRequest.Upload request) {
		return null;
	}

	@PostMapping(value = _ALBUM_FAVOURITE)
	ResponseEntity<AlbumResponse.Whether> doFavourite(@RequestParam(value = FILE_ID) Long fileId) {
		return null;
	}

	@PatchMapping(value = _ALBUM_RESOURCE, consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modifyAlbumName(@PathVariable(value = ALBUM_ID) Long albumId,
										 @RequestBody AlbumRequest.Modify request) {
		return null;
	}

	@DeleteMapping(value = _ALBUM_RESOURCE)
	ResponseEntity<Void> removeAlbum(@PathVariable(value = ALBUM_ID) Long albumId) {
		return null;
	}

	@PostMapping(value = _ALBUM_COMMENTS, consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> createComment(@PathVariable(value = FILE_ID) Long fileId,
									   @RequestBody AlbumRequest.Comment request) {
		return null;
	}

	@PatchMapping(value = _ALBUM_COMMENT_RESOURCE, consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modifyComment(@PathVariable(value = COMMENT_ID) Long commentId) {
		return null;
	}

	@DeleteMapping(value = _ALBUM_COMMENT_RESOURCE)
	ResponseEntity<Void> removeComment(@PathVariable(value = COMMENT_ID) Long commentId) {
		return null;
	}
}
