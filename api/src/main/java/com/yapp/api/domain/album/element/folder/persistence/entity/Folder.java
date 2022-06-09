package com.yapp.api.domain.album.element.folder.persistence.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yapp.api.domain.common.BaseEntity;
import com.yapp.api.domain.family.persistence.entity.Family;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "FOLDER")
@NoArgsConstructor(access = PROTECTED)
public class Folder extends BaseEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String thumbnail;
	private String title;

	@ManyToOne(fetch = LAZY)
	private Family family;

	@Builder
	public Folder(Family family, String thumbnail, String title) {
		this.family = family;
		this.thumbnail = thumbnail;
		this.title = title;
	}
}
