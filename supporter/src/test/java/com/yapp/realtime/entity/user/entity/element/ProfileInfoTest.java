package com.yapp.realtime.entity.user.entity.element;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author : daehwan2yo
 * Date : 2022/08/09
 * Info :
 **/
@DisplayName("[Unit] User's ProfileInfo")
class ProfileInfoTest {
    String 닉네임 = "nickname";
    String 역할 = "role";
    ProfileInfo 프로필정보;

    @BeforeEach
    void init() {
        프로필정보 = ProfileInfo.builder()
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

    @Nested
    class 닉네임관련 {
        @Test
        void 새로운_닉네임_추가() {
            String 새로운닉네임 = "other";

            // putNewNickname()
            프로필정보.patchNickname(2L, 새로운닉네임);

            // originalNickname()
            assertThat(프로필정보.originalNickname()).isEqualTo(닉네임);

            // getNicknameFromOther()
            assertThat(프로필정보.getNicknameFromOther(2L)).isEqualTo(새로운닉네임);
        }

        @Test
        void 새로운_닉네임_을_2번_추가() {
            String 새로운닉네임1 = "other";
            String 새로운닉네임2 = "another";

            // putNewNickname()
            프로필정보.patchNickname(2L, 새로운닉네임1);
            프로필정보.patchNickname(3L, 새로운닉네임2);

            // originalNickname()
            assertThat(프로필정보.originalNickname()).isEqualTo(닉네임);

            // getNicknameFromOther()
            assertThat(프로필정보.getNicknameFromOther(2L)).isEqualTo(새로운닉네임1);

            // getNicknameFromOther()
            assertThat(프로필정보.getNicknameFromOther(3L)).isEqualTo(새로운닉네임2);
        }

        @Test
        void 같은_유저_닉네임_여러번_변경() {
            String 첫번째닉네임 = "other";
            String 두번째닉네임 = "another";

            프로필정보.patchNickname(2L, 첫번째닉네임);

            assertThat(프로필정보.originalNickname()).isEqualTo(닉네임);

            assertThat(프로필정보.getNicknameFromOther(2L)).isEqualTo(첫번째닉네임);


            프로필정보.patchNickname(2L, 두번째닉네임);
            assertThat(프로필정보.getNicknameFromOther(2L)).isEqualTo(두번째닉네임);
        }
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