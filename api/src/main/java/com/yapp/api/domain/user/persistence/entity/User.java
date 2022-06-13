package com.yapp.api.domain.user.persistence.entity;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.yapp.api.domain.common.BaseEntity;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.oauth.entity.OAuthInfo;
import com.yapp.api.domain.user.persistence.entity.element.OAuthInfos;
import com.yapp.api.domain.user.persistence.entity.element.ProfileInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "`USER`")
@NoArgsConstructor(access = PROTECTED)
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private LocalDate birthday;

	@Embedded
	private ProfileInfo profileInfo;

	@Embedded
	private OAuthInfos oAuthInfos;

	@OneToOne(fetch = LAZY)
	private Family family;

	public User(String name, LocalDate birthday, ProfileInfo profileInfo, OAuthInfo... oAuthInfos) {
		this.name = name;
		this.birthday = birthday;
		this.profileInfo = profileInfo;
		this.oAuthInfos = new OAuthInfos(Set.of(oAuthInfos));
	}
}
