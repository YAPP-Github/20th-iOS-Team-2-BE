package com.yapp.core.persistence.user.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yapp.core.persistence.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "PROFILE_MESSAGE")
@NoArgsConstructor(access = PROTECTED)
public class ProfileMessage extends BaseEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String content;

	@ManyToOne(fetch = LAZY)
	private User owner;

	private LocalDateTime date;

	public ProfileMessage(User user, String content, LocalDateTime date) {
		this.owner = user;
		this.content = content;
		this.date = date;
	}

	public static ProfileMessage from(User user, String content, LocalDateTime date) {
		return new ProfileMessage(user, content, date);
	}
}
