package com.yapp.api.domain.album.controller;

import static org.springframework.http.MediaType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.api.domain.album.controller.dto.AlbumResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AlbumQueryApi {
	private static final String DETAILS_AS_KIND_PARAM_DEFAULT = "favourite";

	@GetMapping(value = "/album", produces = APPLICATION_JSON_VALUE)
	ResponseEntity<AlbumResponse.List> retrieveAlbumList() {
		return null;
	}

	@GetMapping(value = "/album/details/{albumId}", produces = APPLICATION_JSON_VALUE)
	ResponseEntity<AlbumResponse.DetailsAsDate> retrieveDetailsAsDate(@PathVariable(value = "albumId") Long albumId) {
		return null;
	}

	@GetMapping(value = "/album/details", produces = APPLICATION_JSON_VALUE)
	ResponseEntity<AlbumResponse.DetailsAsKind> retrieveDetailsAsKind(
		@RequestParam(value = "kind", defaultValue = DETAILS_AS_KIND_PARAM_DEFAULT) String kind) {
		return null;
	}

	@GetMapping(value = "/album/{fileId}/comments", produces = APPLICATION_JSON_VALUE)
	ResponseEntity<AlbumResponse.Comments> retrieveComments(@PathVariable(value = "fileId") Long fileId) {
		return null;
	}
}
