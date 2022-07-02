package com.yapp.api.domain.album.controller;

import static com.yapp.core.constant.ApiConstant.*;
import static org.springframework.http.MediaType.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.api.domain.album.controller.dto.AlbumResponse;
import com.yapp.api.domain.album.element.folder.persistence.entity.Album;
import com.yapp.api.domain.album.element.folder.service.AlbumService;
import com.yapp.api.domain.common.util.validator.ArgumentValidator;
import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.api.global.security.auth.resolver.MustAuthenticated;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AlbumQueryApi {
	private static final String DETAILS_AS_KIND_DEFAULT = "favourite";
	private final ArgumentValidator albumQueryArgumentValidator;
	private final AlbumService albumService;

	@GetMapping(value = _ALBUM, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<AlbumResponse.Elements> retrieveAlbumList(@MustAuthenticated User user,
															 @RequestParam(value = TYPE) String type) {

		if (albumQueryArgumentValidator.equal(KIND, type)) {
			List<Album> albums = albumService.getList(user);
			// N+1 발생, fetch 필요
			Map<String, List<File>> filesByKindName = albums.stream()
															.flatMap(album -> album.getFiles()
																				   .stream())
															.collect(Collectors.groupingBy(file -> file.getKind()
																									   .getValue()));
			filesByKindName.put("favourite",
								albums.stream()
									  .flatMap(album -> album.getFiles()
															 .stream())
									  .filter(File::isFavourite)
									  .collect(Collectors.toList()));

			return ResponseEntity.ok(AlbumResponse.KindElements.from(filesByKindName));
		}

		if (albumQueryArgumentValidator.equal(DATE, type)) {
			return ResponseEntity.ok()
								 .body(new AlbumResponse.DateElements(albumService.getList(user)
																				  .stream()
																				  .map(AlbumResponse.DateElements.AlbumInfo::of)
																				  .collect(Collectors.toList())));
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
