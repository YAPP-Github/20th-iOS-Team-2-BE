package com.yapp.core.persistance.user.entity;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yapp.core.persistance.BaseEntity;
import com.yapp.core.persistance.family.persistence.entity.Family;
import com.yapp.core.persistance.oauth.entity.OAuthInfo;
import com.yapp.core.persistance.user.entity.element.Authority;
import com.yapp.core.persistance.user.entity.element.OAuthInfos;
import com.yapp.core.persistance.user.entity.element.ProfileInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "USERS")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private LocalDate birthday;

	@Enumerated(STRING)
	private Authority authority;

	@Embedded
	private ProfileInfo profileInfo;

	@Embedded
	private OAuthInfos oAuthInfos;

	@ManyToOne(fetch = EAGER)
	private Family family;

	public User(String name, LocalDate birthday, ProfileInfo profileInfo, OAuthInfo... oAuthInfos) {
		this.name = name;
		this.birthday = birthday;
		this.profileInfo = profileInfo;
		this.oAuthInfos = new OAuthInfos(Set.of(oAuthInfos));

		Arrays.stream(oAuthInfos)
			  .forEach(this::connectThisUser);

		authority = Authority.USER;
	}

	private void connectThisUser(OAuthInfo oAuthInfo) {
		oAuthInfo.setUser(this);
	}

	public void update(String name, String nickname, String roleInFamily, LocalDate birthday) {
		this.name = name;
		this.birthday = birthday;
		this.profileInfo = ProfileInfo.of(nickname, roleInFamily);
	}

	public void update(String nickname, String imageLink, LocalDate birthDay, String roleInFamily) {
		if (Objects.nonNull(birthDay) && !this.birthday.isEqual(birthDay)) {
			this.birthday = birthDay;
			// 생일 바뀌었으니, 캘린더의 생일정보에도 수정이 필요하다.
		}
		profileInfo.update(nickname, imageLink, roleInFamily);
	}

	public String getNicknameForUser(User user) {
		return this.profileInfo.getNicknameFromOther(user.id);
	}

	public String getOriNickname() {
		return profileInfo.originalNickname();
	}

	public String getRoleInFamily() {
		return profileInfo.getRoleInFamily();
	}

	public String imageLink() {
		return profileInfo.getImageLink();
	}

	public LocalDateTime getContentLastModified() {
		return getUpdatedAt();
	}

	public int getEmoji() {
		return profileInfo.getEmoji();
	}

	public void setContent(String content) {
		this.profileInfo.setContent(content);
	}

	public void setEmoji(int emoji) {
		this.profileInfo.setEmoji(emoji);
	}

	public boolean isEmpty() {
		return "".equals(getName());
	}

	public static class ANONYMOUS extends User {
		@Override
		public Authority getAuthority() {
			return Authority.ANONYMOUS;
		}
	}

	public void setFamily(Family family) {
		this.family = family;
	}
}
