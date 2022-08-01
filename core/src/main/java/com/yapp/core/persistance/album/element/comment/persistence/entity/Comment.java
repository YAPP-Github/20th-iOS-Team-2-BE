package com.yapp.core.persistance.album.element.comment.persistence.entity;

import static lombok.AccessLevel.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.yapp.core.persistance.BaseEntity;
import com.yapp.core.persistance.family.persistence.entity.Family;
import com.yapp.core.persistance.file.persistence.entity.File;
import com.yapp.core.persistance.user.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Comment extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	private Family family;

	@ManyToOne(fetch = FetchType.LAZY)
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
