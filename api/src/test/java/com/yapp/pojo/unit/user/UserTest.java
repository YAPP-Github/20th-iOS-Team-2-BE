package com.yapp.pojo.unit.user;

import static java.time.LocalDateTime.*;
import static java.time.format.DateTimeFormatter.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yapp.api.domain.oauth.entity.OAuthInfo;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.api.domain.user.persistence.entity.element.ProfileInfo;
import com.yapp.util.EntityFactory;

@DisplayName("User : Entity 단위 테스트")
public class UserTest {

	@Test
	void 정상_ProfileInfo_생성() {
		String 닉네임 = "nickname";
		String 역할 = "아들";
		String 수정일자 = "2022-06-13T08:00";
		String 이미지링크 = "imageLink";

		ProfileInfo 유저정보 = ProfileInfo.of(닉네임, 역할, 이미지링크, parse(수정일자, ISO_LOCAL_DATE_TIME));

		assertThat(유저정보.getNickname()).isEqualTo("0:" + 닉네임);
		assertThat(유저정보.getRoleInFamily()).isEqualTo(역할);
		assertThat(유저정보.getModifiedDate()).isEqualTo(parse(수정일자, ISO_LOCAL_DATE_TIME));
		assertThat(유저정보.getImageLink()).isEqualTo(이미지링크);
		assertThat(유저정보.getEmoji()).isEqualTo(0);
	}

	@Test
	void 정상_constructor_생성() {
		String 이름 = "name";
		String 생일 = "2019-09-04";
		ProfileInfo 프로필정보 = EntityFactory.profileInfo();
		OAuthInfo 소셜정보 = EntityFactory.oAuthInfo_카카오();

		User 회원 = new User(이름, LocalDate.parse(생일, ISO_LOCAL_DATE), 프로필정보, 소셜정보);

		assertThat(회원.getName()).isEqualTo(이름);
		assertThat(회원.getBirthday()).isEqualTo(LocalDate.parse(생일, ISO_LOCAL_DATE));
		assertThat(회원.getProfileInfo()).isEqualTo(프로필정보);
		assertThat(회원.getOAuthInfos()
					 .contains(소셜정보)).isTrue();
	}
}
