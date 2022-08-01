package com.yapp.core.persistance.user.entity.element;

import static com.yapp.core.constant.ServiceConstant.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class ProfileInfo {
	private static final String ORIGINAL = "0:";
	private static final String NICKNAME_SET_SPLITTER = "&";
	private static final String ID_NAME_SPLITTER = ":";

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

		if (Objects.nonNull(imageLink) && !this.imageLink.equals(imageLink)) {
			this.imageLink = imageLink;
		}

		if (Objects.nonNull(roleInFamily) && !this.roleInFamily.equals(roleInFamily)) {
			this.roleInFamily = roleInFamily;
		}
	}

	private void newOriginal(String beforeNickname, String modifiedNickname) {
		nickname = nickname.replace(beforeNickname, modifiedNickname);
	}

	public String originalNickname() {
		return this.nickname.substring(2, nickname.length());
	}

	public String getNicknameFromOther(Long otherUserId) {
		return Arrays.stream(this.nickname.split(NICKNAME_SET_SPLITTER))
					 .filter(nicknameSet -> getIdFromSet(nicknameSet) == otherUserId)
					 .findAny()
					 .orElse(originalNickname());
	}

	private long getIdFromSet(String nicknameSet) {
		return Long.parseLong(nicknameSet.split(ID_NAME_SPLITTER)[0]);
	}

	public void putNewNickname(Long otherUserId, String newNickname) {
		String prefix = otherUserId + ":";

		if (this.nickname.contains(prefix)) {
			for (String nameSet : nickname.split(NICKNAME_SET_SPLITTER)) {
				if (nameSet.startsWith(prefix)) {
					this.nickname = this.nickname.replace(nameSet, prefix + newNickname);
				}
			}
			return;
		}

		nickname = nickname.concat(prefix + newNickname);
	}
}
