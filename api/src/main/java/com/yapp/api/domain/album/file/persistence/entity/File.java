package com.yapp.api.domain.album.file.persistence.entity;

import static com.yapp.api.domain.album.file.persistence.entity.File.Kind.*;
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

import com.yapp.api.domain.album.folder.persistence.entity.Folder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "FILE")
@NoArgsConstructor(access = PROTECTED)
public class File {
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
	private Folder folder;

	private File(String title, String link, Kind kind, Folder folder, LocalDate date) {
		this.title = title;
		this.link = link;
		this.kind = kind;
		this.folder = folder;
		this.date = date;
		this.favourite = false;
	}

	public static File of(String title, String link, String kindName, Folder folder, LocalDate date) {
		if (isPhoto(kindName)) {
			return new File(null, link, PHOTO, folder, date);
		}

		if (isRecording(kindName)) {
			return new File(title, link, RECORDING, folder, date);
		}

		return new INVALID();
	}

	@Getter
	@AllArgsConstructor
	enum Kind {
		PHOTO("photo"), RECORDING("recording"), NULL("null");
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
