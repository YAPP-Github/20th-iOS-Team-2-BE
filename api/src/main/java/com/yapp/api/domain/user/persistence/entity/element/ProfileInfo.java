package com.yapp.api.domain.user.persistence.entity.element;

import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class ProfileInfo {
	private static final String ORIGINAL = "0:";

	// 0:name&targetId:name&targetId:name ...
	private String nickname;
	private String roleInFamily;
	private String imageLink;
	private LocalDateTime modifiedDate;
	private String emoji;

	public static ProfileInfo of(String originalNickname,
								 String roleInFamily,
								 String imageLink,
								 LocalDateTime modifiedDate,
								 String emoji) {
		return new ProfileInfo(original(originalNickname), roleInFamily, imageLink, modifiedDate, emoji);
	}

	private static String original(String originalNickname) {
		return ORIGINAL + originalNickname;
	}
}
