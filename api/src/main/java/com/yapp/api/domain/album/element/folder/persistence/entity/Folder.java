package com.yapp.api.domain.album.element.folder.persistence.entity;

import static java.time.format.DateTimeFormatter.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yapp.api.domain.common.BaseEntity;
import com.yapp.api.domain.family.persistence.entity.Family;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "FOLDER")
@NoArgsConstructor(access = PROTECTED)
public class Folder extends BaseEntity {
	private static final String DEFAULT_TITLE_POSTFIX = " 앨범";

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Setter
	private String thumbnail = "";
	private String title;
	private LocalDate date;

	@ManyToOne(fetch = LAZY)
	private Family family;

	public Folder(Family family, LocalDate date) {
		this.family = family;
		this.date = date;
		this.title = defaultTitle(date);
	}

	private String defaultTitle(LocalDate date) {
		String createdDate = date.format(ISO_LOCAL_DATE);
		return createdDate + DEFAULT_TITLE_POSTFIX;
	}
}
