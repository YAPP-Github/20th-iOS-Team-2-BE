package com.yapp.core.persistance.file.persistence.entity;

import static com.yapp.core.persistance.file.persistence.entity.File.Kind.*;
import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.yapp.core.persistance.BaseEntity;
import com.yapp.core.persistance.album.element.comment.persistence.entity.Comment;
import com.yapp.core.persistance.album.element.folder.persistence.entity.Album;
import com.yapp.core.persistance.family.persistence.entity.Family;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "FILE")
@NoArgsConstructor(access = PROTECTED)
public class File extends BaseEntity {
	public static final File INVALID = new File.INVALID();
	public static final String KIND_PHOTO = "photo";
	public static final String KIND_RECORDING = "recording";
	public static final String KIND_NULL = "null";
	public static final String FAVOURITE = "favourite";

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String title;
	private String link;
	private boolean favourite;
	private LocalDateTime dateTime;

	@Enumerated(STRING)
	private Kind kind;

	@ManyToOne(fetch = LAZY)
	private Album album;

	@ManyToOne(fetch = LAZY)
	private Family family;

	@OneToMany(mappedBy = "file", fetch = LAZY)
	private List<Comment> commentList;

	private File(String title, String link, Kind kind, Album album, LocalDateTime dateTime, Family family) {
		this.title = title;
		this.link = link;
		this.kind = kind;
		this.album = album;
		this.dateTime = dateTime;
		this.favourite = false;
		this.family = family;
	}

	public static File of(String title,
						  String link,
						  String kindName,
						  Album album,
						  LocalDateTime dateTime,
						  Family family) {
		if (kindIsPhoto(kindName)) {
			return new File(null, link, PHOTO, album, dateTime, family);
		}

		if (kindIsRecording(kindName)) {
			return new File(title, link, RECORDING, album, dateTime, family);
		}

		return INVALID;
	}

	public boolean doFavour() {
		this.favourite = !this.favourite;
		return favourite;
	}

	public boolean isPhoto() {
		return this.kind == PHOTO;
	}

	public boolean isRecording() {
		return this.kind == RECORDING;
	}

	public void modifyDate(LocalDateTime date) {
		this.dateTime = date;
	}

	public int getCommentCount() {
		return commentList.size();
	}

	public void modifyAlbum(Album album) {
		this.album.removeFile(this);

		this.album = album;
	}

	@Getter
	@AllArgsConstructor
	public enum Kind {
		PHOTO(KIND_PHOTO), RECORDING(KIND_RECORDING), NULL(KIND_NULL);
		private final String value;

		static boolean kindIsPhoto(String kindName) {
			return PHOTO.getValue()
						.equalsIgnoreCase(kindName);
		}

		static boolean kindIsRecording(String kindName) {
			return RECORDING.getValue()
							.equalsIgnoreCase(kindName);
		}
	}

	private static class INVALID extends File {
		@Override
		public Kind getKind() {
			return NULL;
		}

		@Override
		public String getLink() {return "";}
	}
}
