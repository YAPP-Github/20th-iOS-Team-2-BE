package com.yapp.api.domain.file.controller;

import com.yapp.api.domain.file.controller.model.FileRequest;
import com.yapp.api.domain.file.controller.model.FileResponse;
import com.yapp.api.domain.file.service.CloudService;
import com.yapp.api.domain.file.service.FileService;
import com.yapp.api.domain.folder.service.AlbumService;
import com.yapp.api.global.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.realtime.entity.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.yapp.realtime.constant.ApiConstant.FILE_ID;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequiredArgsConstructor
public class FileCommandApi {
    private final CloudService cloudService;
    private final AlbumService albumService;
    private final FileService fileService;

    @PostMapping(value = "/files", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<FileResponse.UploadFiles> uploadFiles(
            @AuthenticationHasFamily User user,
            @RequestPart List<MultipartFile> files) {
        List<String> fileLinks = cloudService.upload(files);

        return ResponseEntity.ok((new FileResponse.UploadFiles(fileLinks)));
    }

    @DeleteMapping(value = "/files/{fileId}")
    ResponseEntity<Void> removeFile(
            @AuthenticationHasFamily User user,
            @PathVariable(value = FILE_ID) Long fileId) {
        fileService.remove(user.getFamily(), fileId);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/files/{fileId}/date")
    ResponseEntity<Void> modifyDate(
            @AuthenticationHasFamily User user,
            @PathVariable(value = "fileId") Long fileId,
            @RequestBody FileRequest.Date request) {
        albumService.modifyFileDate(user.getFamily(), fileId, request.getDate());

        return ResponseEntity.ok()
                .build();
    }
}
