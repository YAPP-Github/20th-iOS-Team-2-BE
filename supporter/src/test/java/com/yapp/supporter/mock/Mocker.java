package com.yapp.supporter.mock;

import com.yapp.supporter.constant.OAuthProvider;
import com.yapp.supporter.entity.oauth.entity.OAuthInfo;
import com.yapp.supporter.entity.user.entity.User;
import com.yapp.supporter.entity.user.entity.element.ProfileInfo;

import java.time.LocalDate;

/**
 * Author : daehwan2yo
 * Date : 2022/08/21
 * Info :
 **/
public class Mocker {
    public static User user() {
        return User.builder()
                .name("randomName")
                .birthday(LocalDate.of(2010, 10, 10))
                .oAuthInfo(OAuthInfo.of(OAuthProvider.KAKAO, "abcde"))
                .profileInfo(ProfileInfo.builder()
                        .firstNickname("randomNickname")
                        .roleInFamily("son")
                        .build())
                .build();
    }
}
