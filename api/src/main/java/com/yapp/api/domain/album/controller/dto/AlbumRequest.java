package com.yapp.api.domain.album.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AlbumRequest {
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Upload {
		@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
		private LocalDateTime date;
		private String title;
		private List<String> links = new ArrayList<>();
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Modify {
		private String albumName;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Comment {
		private String content;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ModifyDate {
		@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
		private LocalDateTime date;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Delegate {
		private Long fileId;
	}
}
