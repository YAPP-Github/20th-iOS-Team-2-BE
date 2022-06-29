package com.yapp.api.domain.user.persistence.entity.element;

import static com.yapp.core.constant.ServiceConstant.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

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
}
