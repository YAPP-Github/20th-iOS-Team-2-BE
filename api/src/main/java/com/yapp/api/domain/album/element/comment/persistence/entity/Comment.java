package com.yapp.api.domain.album.element.comment.persistence.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.yapp.api.domain.common.BaseEntity;
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

	@OneToOne(fetch = LAZY)
	private User user;

	public Comment(User user, String content) {
		this.user = user;
		this.content = content;
	}
}
