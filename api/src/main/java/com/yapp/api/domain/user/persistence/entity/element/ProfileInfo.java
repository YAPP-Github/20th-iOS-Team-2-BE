package com.yapp.api.domain.user.persistence.entity.element;

import static com.yapp.core.constant.ServiceConstant.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class ProfileInfo {
	private static final String ORIGINAL = "0:";

	// 0:name&targetId:name&targetId:name ...
	private String nickname;
	private String roleInFamily;
	private String imageLink;
	private LocalDateTime modifiedDate;
	private Integer emoji;

	private ProfileInfo(String originalNickname, String roleInFamily) {
		this.nickname = originalNickname;
		this.roleInFamily = roleInFamily;
		this.imageLink = DEFAULT_IMAGE;
		this.modifiedDate = LocalDateTime.now();
		this.emoji = 0;
	}

	public static ProfileInfo of(String originalNickname, String roleInFamily) {
		return new ProfileInfo(original(originalNickname), roleInFamily);
	}

	private static String original(String originalNickname) {
		return ORIGINAL + originalNickname;
	}

	public void update(String nickname, String imageLink, String roleInFamily) {
		String originalNickname = originalNickname();
		if (Objects.nonNull(nickname) && !originalNickname.equals(nickname)) {
			newOriginal(originalNickname, nickname);
		}

		if(Objects.nonNull(imageLink) && !this.imageLink.equals(imageLink)) {
			this.imageLink = imageLink;
		}

		if(Objects.nonNull(roleInFamily) && !this.roleInFamily.equals(roleInFamily)) {
			this.roleInFamily = roleInFamily;
		}
	}

	private void newOriginal(String beforeNickname, String modifiedNickname) {
		nickname = nickname.replace(beforeNickname, modifiedNickname);
	}

	private String originalNickname() {
		return this.nickname.substring(2, nickname.length());
	}
}
