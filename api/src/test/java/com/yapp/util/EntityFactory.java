package com.yapp.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.yapp.api.domain.album.element.folder.persistence.entity.Album;
import com.yapp.api.domain.calendar.element.appointment.persistence.entity.Appointment;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.file.persistence.entity.File;
import com.yapp.api.domain.oauth.entity.OAuthInfo;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.api.domain.user.persistence.entity.element.ProfileInfo;

public class EntityFactory {
	public static OAuthInfo oAuthInfo_카카오() {
		return OAuthInfo.of("kakao", "1234");
	}

	public static OAuthInfo oAuthInfo_애플() {
		return OAuthInfo.of("apple", "1234");
	}

	public static ProfileInfo profileInfo() {
		return ProfileInfo.of("닉네임", "아들", "imageLink", LocalDateTime.of(2022, 6, 13, 20, 0));
	}

	public static User user() {
		return new User("이름", LocalDate.of(2019, 9, 4), profileInfo(), oAuthInfo_카카오(), oAuthInfo_애플());
	}

	public static Family family() {
		return new Family(user(), "가족명", "가훈");
	}

	public static Family family(User 만든이) {
		return new Family(만든이, "가족명", "가훈");
	}

	public static Appointment appointment(User 회원) {
		return new Appointment(family(), user(), "일정제목", "일정내용", LocalDateTime.of(2022, 6, 13, 20, 0));
	}

	public static User anonymousUser() {
		return new User.ANONYMOUS();
	}

	public static Album album(Family 가족, LocalDate 날짜) {
		return new Album(가족, 날짜);
	}

	public static File file(String 제목, String 링크, String 종류, Album 앨범, LocalDate 날짜, Family 가족) {
		return File.of(제목, 링크, 종류, 앨범, 날짜, 가족);
	}
}
