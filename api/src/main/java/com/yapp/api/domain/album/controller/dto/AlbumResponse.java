package com.yapp.api.domain.album.controller.dto;

import static java.time.format.DateTimeFormatter.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.yapp.api.domain.album.element.folder.persistence.entity.Album;
import com.yapp.api.domain.file.persistence.entity.File;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AlbumResponse {
	private static final String FAVOURITE = "favourite";
	private static final String PHOTO = "photo";
	private static final String RECORDING = "recording";

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
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Comments {
		private List<CommentElement> comments = new ArrayList<>();

		public static Comments from(List<CommentElement> list) {
			return new Comments(list);
		}
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CommentElement {
		private String profileLink;
		private String nickname;
		private String roleInFamily;
		private String createdDate;
		private String content;
	}

	public interface Elements {}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class DateElements extends AlbumResponse implements Elements {
		private List<AlbumInfo> albums = new ArrayList<>();

		@Getter
		@NoArgsConstructor
		@AllArgsConstructor(access = AccessLevel.PRIVATE)
		public static class AlbumInfo {
			private Long albumId;
			private String title;
			private String thumbnail;
			private String date;

			public static AlbumInfo of(Album album) {
				return new AlbumInfo(album.getId(),
									 album.getTitle(),
									 album.getThumbnail(),
									 album.getDate()
										  .format(ISO_DATE));
			}
		}
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class KindElements extends AlbumResponse implements Elements {
		private KindDetail favourite;
		private KindDetail photo;
		private KindDetail recording;

		public static KindElements from(Map<String, List<File>> filesByKindName) {

			return new KindElements(new KindDetail(FAVOURITE, getSize(filesByKindName, FAVOURITE)),
									new KindDetail(PHOTO, getSize(filesByKindName, PHOTO)),
									new KindDetail(RECORDING, getSize(filesByKindName, RECORDING))
			);
		}

		private static int getSize(Map<String, List<File>> filesByKindName, String recording) {
			if(Objects.isNull(filesByKindName.get(recording))) {
				return 0;
			}
			return filesByKindName.get(recording)
								  .size();
		}

		@Getter
		@NoArgsConstructor
		@AllArgsConstructor
		public static class KindDetail {
			private String kind;
			private int count;
		}
	}
}
