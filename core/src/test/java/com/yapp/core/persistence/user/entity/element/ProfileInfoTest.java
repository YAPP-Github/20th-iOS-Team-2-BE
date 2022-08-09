package com.yapp.core.persistence.user.entity.element;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author : daehwan2yo
 * Date : 2022/08/09
 * Info :
 **/
class ProfileInfoTest {
    String 닉네임 = "nickname";
    String 역할 = "role";
    ProfileInfo 프로필정보;

    @BeforeEach
    void init() {
        ProfileInfo 프로필정보 = ProfileInfo.builder()
                .firstNickname(닉네임)
                .roleInFamily(역할)
                .build();
    }

    @Test
    void create() {
        assertThat(프로필정보.getEmoji()).isEqualTo(0);
        assertThat(프로필정보.getNickname()).isEqualTo("0:" + 닉네임);
        assertThat(프로필정보.getRoleInFamily()).isEqualTo(역할);
        assertThat(프로필정보.getImageLink()).isEqualTo("defaultImage");
        assertThat(프로필정보.getContent()).isNull();
    }

    @Test
    void nickname() {
        String 새로운닉네임 = "other";

        // putNewNickname()
        프로필정보.putNewNickname(2L, 새로운닉네임);

        // originalNickname()
        assertThat(프로필정보.originalNickname()).isEqualTo(닉네임);

        // getNicknameFromOther()
        assertThat(프로필정보.getNicknameFromOther(2L)).isEqualTo(새로운닉네임);
    }

    @Test
    void updateContent() {
        String 상태메시지 = "message";

        프로필정보.updateContent(상태메시지);

        assertThat(프로필정보.getContent()).isEqualTo(상태메시지);
    }

    @Test
    void updateEmoji() {
        int 이모지 = 4;

        프로필정보.updateEmoji(이모지);

        assertThat(프로필정보.getEmoji()).isEqualTo(이모지);
    }
}