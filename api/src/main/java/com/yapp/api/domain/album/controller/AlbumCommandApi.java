package com.yapp.api.domain.album.controller;

import static org.springframework.http.MediaType.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yapp.api.domain.album.controller.dto.AlbumRequest;
import com.yapp.api.domain.album.controller.dto.AlbumResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AlbumCommandApi {
	@PostMapping(value = "/album/files", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<AlbumResponse.UploadFiles> uploadFiles(@RequestPart List<MultipartFile> files) {
		return null;
	}

	@PostMapping(value = "/album/photos", consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> uploadPhotos(@RequestBody AlbumRequest.Upload request) {
		return null;
	}

	@PostMapping(value = "/album/recordings", consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> uploadRecordings(@RequestBody AlbumRequest.Upload request) {
		return null;
	}

	@PostMapping(value = "/album/favourite")
	ResponseEntity<AlbumResponse.Whether> doFavourite(@RequestParam(value = "fileId") Long fileId) {
		return null;
	}

	@PatchMapping(value = "/album/{albumId}", consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modifyAlbumName(@PathVariable(value = "albumId") Long albumId,
										 @RequestBody AlbumRequest.Modify request) {
		return null;
	}

	@DeleteMapping(value = "/album/{albumId}")
	ResponseEntity<Void> removeAlbum(@PathVariable(value = "albumId") Long albumId) {
		return null;
	}

	@DeleteMapping(value = "/album/{fileId}")
	ResponseEntity<Void> removeFile(@PathVariable(value = "fileId") Long fileId) {
		return null;
	}

	@PostMapping(value = "/album/{fileId}/comments", consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> createComment(@PathVariable(value = "fileId") Long fileId,
									   @RequestBody AlbumRequest.Comment request) {
		return null;
	}

	@PatchMapping(value = "/album/comments/{commentId}", consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modifyComment(@PathVariable(value = "commentId") Long commentId) {
		return null;
	}

	@DeleteMapping(value = "/album/comments/{commentId}")
	ResponseEntity<Void> removeComment(@PathVariable(value = "commentId") Long commentId) {
		return null;
	}
}
