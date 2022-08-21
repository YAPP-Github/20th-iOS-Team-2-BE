package com.yapp.api.domain.user.controller.model;

import com.yapp.realtime.entity.user.entity.ProfileMessage;
import com.yapp.realtime.entity.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProfileResponse {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MessageHistory {
        private String nickname;
        private String roleInFamily;
        private String profileLink;
        private int count;
        private List<MessageDetail> history = new ArrayList<>();

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class MessageDetail {
            private Long messageId;
            private String content;
            private LocalDateTime createdDate;

            public static MessageDetail from(ProfileMessage profileMessage) {
                return new MessageDetail(profileMessage.getId(), profileMessage.getContent(), profileMessage.getDate());
            }
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserSimple {
        private Long userId;
        private String nickname;
        private String name;
        private String roleInfamily;
        private String imageLink;

        public static UserSimple from(User user) {
            return new UserSimple(user.getId(), user.getOriNickname(), user.getName(), user.getRoleInFamily(), user.imageLink());
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDetail {
        private String nickname;
        private String name;
        private String roleInFamily;
        private LocalDate birth;
        private String imageLink;

        public static UserDetail from(User user) {
            return new UserDetail(user.getOriNickname(), user.getName(), user.getRoleInFamily(), user.getBirthday(), user.imageLink());
        }
    }
}
