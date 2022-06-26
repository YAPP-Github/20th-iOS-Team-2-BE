package com.yapp.api.domain.album.element.comment.persistence.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.yapp.api.domain.common.BaseEntity;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.user.persistence.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Comment extends BaseEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String content;

	@ManyToOne(fetch = LAZY)
	private User user;

	@ManyToOne(fetch = LAZY)
	private Family family;

	@ManyToOne(fetch = LAZY)
	private File file;

	public Comment(User user, Family family, File file, String content) {
		this.user = user;
		this.family = family;
		this.file = file;
		this.content = content;
	}

	public void modifyComment(String toBe) {
		this.content = toBe;
	}
}
