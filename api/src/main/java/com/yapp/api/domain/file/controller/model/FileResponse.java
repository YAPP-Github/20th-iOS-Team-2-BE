package com.yapp.api.domain.file.controller.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FileResponse {
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class UploadFiles {
		private List<String> links = new ArrayList<>();
	}
}
