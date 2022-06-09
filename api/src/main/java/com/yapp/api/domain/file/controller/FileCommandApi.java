package com.yapp.api.domain.file.controller;

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
	@PostMapping(value = "/files", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<AlbumResponse.UploadFiles> uploadFiles(@RequestPart List<MultipartFile> files) {
		return null;
	}

	@DeleteMapping(value = "/album/{fileId}")
	ResponseEntity<Void> removeFile(@PathVariable(value = "fileId") Long fileId) {
		return null;
	}
}
