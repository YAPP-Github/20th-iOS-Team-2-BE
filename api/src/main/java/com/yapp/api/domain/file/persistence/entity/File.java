package com.yapp.api.domain.file.persistence.entity;

import static com.yapp.api.domain.file.persistence.entity.File.Kind.*;
import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yapp.api.domain.album.element.folder.persistence.entity.Album;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "FILE")
@NoArgsConstructor(access = PROTECTED)
public class File {
	public static final String KIND_PHOTO = "photo";
	public static final String KIND_RECORDING = "recording";
	public static final String KIND_NULL = "null";

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String title;
	private String link;
	private boolean favourite;
	private LocalDate date;

	@Enumerated(STRING)
	private Kind kind;

	@ManyToOne(fetch = LAZY)
	private Album album;

	private File(String title, String link, Kind kind, Album album, LocalDate date) {
		this.title = title;
		this.link = link;
		this.kind = kind;
		this.album = album;
		this.date = date;
		this.favourite = false;
	}

	public static File of(String title, String link, String kindName, Album album, LocalDate date) {
		if (isPhoto(kindName)) {
			return new File(null, link, PHOTO, album, date);
		}

		if (isRecording(kindName)) {
			return new File(title, link, RECORDING, album, date);
		}

		return new INVALID();
	}

	@Getter
	@AllArgsConstructor
	enum Kind {
		PHOTO(KIND_PHOTO), RECORDING(KIND_RECORDING), NULL(KIND_NULL);
		private final String value;

		static boolean isPhoto(String kindName) {
			return PHOTO.getValue()
						.equalsIgnoreCase(kindName);
		}

		static boolean isRecording(String kindName) {
			return RECORDING.getValue()
							.equalsIgnoreCase(kindName);
		}
	}

	private static class INVALID extends File {
		@Override
		public Kind getKind() {
			return NULL;
		}
	}
}
