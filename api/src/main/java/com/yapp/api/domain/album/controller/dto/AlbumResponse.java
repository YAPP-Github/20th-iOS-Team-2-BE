package com.yapp.api.domain.album.controller.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AlbumResponse {

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class DetailsAsDate {
		private String title;
		private List<AlbumDetail> elements = new ArrayList<>();
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class DetailsAsKind {
		private String type;
		private List<AlbumDetail> elements = new ArrayList<>();
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AlbumDetail {
		private String kind;
		private Long fileId;
		private String date;
		private String link;
		private boolean favourite;
		private int commentCount;
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Whether {
		private Boolean result;
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Comments {
		private String content;
	}

	public interface Elements {}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class DateElements implements Elements {
		private List<AlbumInfo> albums = new ArrayList<>();

		@Getter
		@NoArgsConstructor
		@AllArgsConstructor
		public static class AlbumInfo {
			private Long albumId;
			private String title;
			private String thumbnail;
			private String date;
		}
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class KindElements implements Elements {
		private KindDetail favourite;
		private KindDetail photo;
		private KindDetail recording;

		@Getter
		@NoArgsConstructor
		@AllArgsConstructor
		public static class KindDetail {
			private String kind;
			private int count;
		}
	}
}
