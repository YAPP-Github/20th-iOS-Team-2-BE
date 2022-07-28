package com.yapp.api.domain.file.controller;

import static com.yapp.core.constant.ApiConstant.*;
import static org.springframework.http.MediaType.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yapp.api.domain.album.element.folder.service.AlbumService;
import com.yapp.api.domain.file.controller.dto.FileResponse;
import com.yapp.api.domain.file.service.CloudService;
import com.yapp.api.domain.file.service.FileService;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.api.global.security.auth.resolver.AuthenticationHasFamily;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RestController
@RequiredArgsConstructor
public class FileCommandApi {

	private final CloudService cloudService;
	private final AlbumService albumService;

	// sync
	@PostMapping(value = _FILES, consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<FileResponse.UploadFiles> uploadFiles(@RequestPart List<MultipartFile> files) {
		List<String> fileLinks = cloudService.upload(files);

		return ResponseEntity.ok((new FileResponse.UploadFiles(fileLinks)));
	}

	@Deprecated
	@DeleteMapping(value = _FILES_RESOURCE)
	ResponseEntity<Void> removeFile(@PathVariable(value = FILE_ID) Long fileId) {
		return null;
	}

	@PatchMapping("/files/{fileId}/date")
	ResponseEntity<Void> modifyDate(@AuthenticationHasFamily User user,
									@PathVariable(value = "fileId") Long fileId,
									@RequestBody Request.Date request) {
		albumService.modifyFileDate(user, fileId, request.getDate());

		return ResponseEntity.ok()
							 .build();
	}

	public class Request {
		@Getter
		@Setter
		@NoArgsConstructor
		@AllArgsConstructor
		public class Date {
			private LocalDateTime date;
		}
	}
}
