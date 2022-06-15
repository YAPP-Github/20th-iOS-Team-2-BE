package com.yapp.api.domain.album.element.folder.persistence.entity;

import static java.time.format.DateTimeFormatter.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yapp.api.domain.common.BaseEntity;
import com.yapp.api.domain.family.persistence.entity.Family;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "FOLDER")
@NoArgsConstructor(access = PROTECTED)
public class Folder extends BaseEntity {
	private static final String DEFAULT_TITLE_POSTFIX = " 앨범";

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String thumbnail;
	private String title;

	@ManyToOne(fetch = LAZY)
	private Family family;

	public Folder(Family family, String thumbnail) {
		this.family = family;
		this.thumbnail = thumbnail;
	}

	public void setDefaultTitleAsCreatedAt() {
		this.title = defaultTitle(getCreatedAt());
	}

	private String defaultTitle(LocalDateTime createdAt) {
		String createdDate = createdAt.format(ISO_LOCAL_DATE);
		return createdDate + DEFAULT_TITLE_POSTFIX;
	}
}
