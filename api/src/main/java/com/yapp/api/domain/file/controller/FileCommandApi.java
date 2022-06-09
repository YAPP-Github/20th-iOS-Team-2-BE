package com.yapp.api.domain.file.controller;

import static com.yapp.core.constant.ApiConstant.*;
import static org.springframework.http.MediaType.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yapp.api.domain.album.controller.dto.AlbumResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileCommandApi {
	@PostMapping(value = _FILES, consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<AlbumResponse.UploadFiles> uploadFiles(@RequestPart List<MultipartFile> files) {
		return null;
	}

	@DeleteMapping(value = _FILES_RESOURCE)
	ResponseEntity<Void> removeFile(@PathVariable(value = FILE_ID) Long fileId) {
		return null;
	}
}
