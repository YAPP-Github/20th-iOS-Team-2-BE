package com.yapp.allinone.domain.file.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class FileResponse {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UploadFiles {
        private List<String> links = new ArrayList<>();
    }
}
