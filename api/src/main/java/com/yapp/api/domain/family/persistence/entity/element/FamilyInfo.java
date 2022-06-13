package com.yapp.api.domain.family.persistence.entity.element;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.user.persistence.entity.User;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class FamilyInfo {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String name;
	private String motto;
	private String imageLink;

	@ManyToOne(fetch = LAZY)
	private Family family;

	@ManyToOne(fetch = LAZY)
	private User user;

	public FamilyInfo(User user, String name, String motto, String imageLink) {
		this.user = user;
		this.name = name;
		this.motto = motto;
		this.imageLink = imageLink;
	}

	public void setFamily(Family family) {
		this.family = family;
	}
}
