package com.yapp.api.domain.album.controller;

import static com.yapp.core.constant.ApiConstant.*;
import static org.springframework.http.MediaType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.api.domain.album.controller.dto.AlbumResponse;
import com.yapp.api.domain.common.util.validator.ArgumentValidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AlbumQueryApi {
	private static final String DETAILS_AS_KIND_DEFAULT = "favourite";
	private final ArgumentValidator albumQueryArgumentValidator;

	@GetMapping(value = _ALBUM, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<AlbumResponse.Elements> retrieveAlbumList(@RequestParam(value = TYPE) String type) {
		if (albumQueryArgumentValidator.equal(KIND, type)) {
			return ResponseEntity.ok()
								 .body(new AlbumResponse.DateElements());
		}

		if (albumQueryArgumentValidator.equal(DATE, type)) {
			return ResponseEntity.ok()
								 .body(new AlbumResponse.KindElements());
		}
		return null;
	}

	@GetMapping(value = _ALBUM_DETAILS_RESOURCE, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<AlbumResponse.DetailsAsDate> retrieveDetailsAsDate(@PathVariable(value = ALBUM_ID) Long albumId) {
		return null;
	}

	@GetMapping(value = _ALBUM_DETAILS, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<AlbumResponse.DetailsAsKind> retrieveDetailsAsKind(
		@RequestParam(value = KIND, defaultValue = DETAILS_AS_KIND_DEFAULT) String kind) {
		return null;
	}

	@GetMapping(value = _ALBUM_COMMENTS, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<AlbumResponse.Comments> retrieveComments(@PathVariable(value = FILE_ID) Long fileId) {
		return null;
	}
}
