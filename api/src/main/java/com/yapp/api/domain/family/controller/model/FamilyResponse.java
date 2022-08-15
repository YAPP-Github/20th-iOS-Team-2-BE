package com.yapp.api.domain.family.controller.model;

import com.yapp.core.entity.family.persistence.entity.Family;
import com.yapp.core.entity.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FamilyResponse {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private Long familyId;
        private String familyCode;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeInfo {
        private String familyName;
        private List<EventInfo> events = new ArrayList<>();

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class EventInfo {
            private String title;
            private String eventDate;
            private String color;
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Notifications {
        private List<Notification> notifications = new ArrayList<>();

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Notification {
            private String type;
            private String content;
            private Long targetId;
            private String createdDate;
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Info {
        private String familyName;
        private String familyMotto;
        private List<MemberInfo> members;

        public static Info from(Family family, User user) {
            List<MemberInfo> nicknames = family.getMembers()
                    .stream()
                    .filter(member -> !member.equals(user))
                    .map(member -> MemberInfo.from(user, member))
                    .collect(Collectors.toList());

            return new Info(family.getName(), family.getMotto(), nicknames);
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberInfo {
        private Long userId;
        private String name;
        private String imageLink;
        private String nickname;
        private String roleInFamily;

        public static MemberInfo from(User me, User target) {
            return new MemberInfo(target.getId(), target.getName(), target.imageLink(), target.getNicknameForUser(me), target.getRoleInFamily());
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Code {
        private String code;

        public static Code from(String code) {
            return new Code(code);
        }
    }
}
