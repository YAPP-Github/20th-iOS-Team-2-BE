package com.yapp.core.persistance.user.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yapp.core.persistance.BaseEntity;

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

	public ProfileMessage(User user, String content) {
		this.owner = user;
		this.content = content;
	}
}
