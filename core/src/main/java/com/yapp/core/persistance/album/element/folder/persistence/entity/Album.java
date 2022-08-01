package com.yapp.core.persistance.album.element.folder.persistence.entity;

import static java.time.format.DateTimeFormatter.*;
import static java.util.Objects.*;
import static lombok.AccessLevel.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.yapp.core.persistance.BaseEntity;
import com.yapp.core.persistance.family.persistence.entity.Family;
import com.yapp.core.persistance.file.persistence.entity.File;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "ALBUM")
@NoArgsConstructor(access = PROTECTED)
public class Album extends BaseEntity {
	private static final String DEFAULT_TITLE_POSTFIX = " 앨범";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	private String thumbnail = "";
	private String title;
	private LocalDate date;

	@ManyToOne(fetch = FetchType.LAZY)
	private Family family;

	@OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
	private List<File> files;

	public Album(Family family, LocalDate date) {
		this.family = family;
		this.date = date;
		this.title = defaultTitle(date);
	}

	private String defaultTitle(LocalDate dateTime) {
		String createdDate = dateTime.format(ISO_LOCAL_DATE);
		return createdDate + DEFAULT_TITLE_POSTFIX;
	}

	public boolean noThumbnail() {
		return thumbnail.isBlank();
	}

	public void modifyTitle(String toBe) {
		if (!isNull(toBe) && !toBe.isEmpty()) {
			this.title = toBe;
		}
	}

	public void modifyDate(LocalDateTime dateTime) {
		if (!isNull(dateTime)) {
			this.date = dateTime.toLocalDate();
			// occurred SELECT N+1
			files.forEach(file -> file.modifyDate(dateTime));
		}
	}

	public boolean contains(File file) {
		return files.contains(file);
	}

	public void removeFile(File file) {
		files.remove(file);
	}
}
