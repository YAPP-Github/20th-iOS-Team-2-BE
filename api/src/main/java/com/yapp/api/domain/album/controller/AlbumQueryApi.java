package com.yapp.api.domain.album.controller;

import static com.yapp.core.constant.ApiConstant.*;
import static org.springframework.http.MediaType.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.api.domain.album.controller.dto.AlbumResponse;
import com.yapp.api.domain.album.service.CommentService;
import com.yapp.core.persistence.folder.album.persistence.entity.Album;
import com.yapp.api.domain.album.service.AlbumService;
import com.yapp.api.domain.common.response.PageResponse;
import com.yapp.core.util.validator.ArgumentValidator;
import com.yapp.core.persistence.file.persistence.entity.File;
import com.yapp.api.domain.file.service.FileService;
import com.yapp.core.persistence.user.entity.User;
import com.yapp.api.global.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.api.global.security.auth.resolver.MustAuthenticated;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AlbumQueryApi {
	private static final String DETAILS_AS_KIND_DEFAULT = "favourite";
	private final ArgumentValidator albumQueryArgumentValidator;
	private final AlbumService albumService;
	private final CommentService commentService;
	private final FileService fileService;

	@GetMapping(value = _ALBUM, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<?> retrieveAlbumList(@AuthenticationHasFamily User user,
										@RequestParam(value = TYPE) String type,
										@PageableDefault Pageable pageable) {

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
			Page<Album> page = albumService.getList(user, pageable);

			return ResponseEntity.ok()
								 .body(PageResponse.of(new AlbumResponse.DateElements(page.getContent()
																						  .stream()
																						  .map(AlbumResponse.DateElements.AlbumInfo::of)
																						  .collect(Collectors.toList())),
													   page));
		}
		return ResponseEntity.badRequest()
							 .build();
	}

	@GetMapping(value = _ALBUM_DETAILS_RESOURCE, produces = APPLICATION_JSON_VALUE)
	PageResponse<AlbumResponse.DetailsAsDate> retrieveDetailsAsDate(@AuthenticationHasFamily User user,
																	@PathVariable(value = ALBUM_ID) Long albumId,
																	@PageableDefault Pageable pageable) {
		Album album = albumService.get(user, albumId);
		Page<File> files = fileService.getFiles(user, album, pageable);
		return PageResponse.of(AlbumResponse.DetailsAsDate.of(album,
															  files.getContent()
																   .stream()
																   .map(AlbumResponse.AlbumDetail::from)
																   .collect(Collectors.toList())), files);
	}

	@GetMapping(value = _ALBUM_DETAILS, produces = APPLICATION_JSON_VALUE)
	PageResponse<AlbumResponse.DetailsAsKind> retrieveDetailsAsKind(
		@AuthenticationHasFamily User user,
		@RequestParam(value = KIND, defaultValue = DETAILS_AS_KIND_DEFAULT) String kind,
		@PageableDefault Pageable pageable) {

		Page<File> files = fileService.getFiles(user, kind.toUpperCase(), pageable);

		return PageResponse.of(AlbumResponse.DetailsAsKind.of(kind,
															  files.getContent().stream()
																   .map(AlbumResponse.AlbumDetail::from)
																   .collect(Collectors.toList())),
							   files);
	}

	@GetMapping(value = _ALBUM_COMMENTS, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<AlbumResponse.Comments> retrieveComments(@MustAuthenticated User user,
															@PathVariable(value = FILE_ID) Long fileId) {
		return ResponseEntity.ok(AlbumResponse.Comments.from(commentService.getList(user, fileId)));
	}
}
