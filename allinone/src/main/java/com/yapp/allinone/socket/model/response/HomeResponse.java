package com.yapp.allinone.socket.model.response;

import com.yapp.supporter.entity.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author : daehwan2yo
 * Date : 2022/07/31
 * Info :
 **/
public class HomeResponse {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeStatusInfo {
        private List<HomeMemberInfo> members = new ArrayList<>();

        public static HomeStatusInfo of(User user, List<User> members) {
            return new HomeStatusInfo(members.stream()
                    .map(member -> HomeMemberInfo.of(member, user))
                    .collect(Collectors.toList()));
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HomeMemberInfo {
        private Long userId;
        private String image;
        private String nickname;
        private String role;
        private String updatedAt;
        private int emoji;
        private String content;

        public static HomeMemberInfo of(User user, User ori) {
            return new HomeMemberInfo(user.getId(), user.imageLink(), user.getNicknameForUser(ori), user.getRoleInFamily(), user.getContentLastModified()
                    .format(DateTimeFormatter.ISO_DATE_TIME), user.getEmoji(), user.getProfileInfo()
                    .getContent());
        }
    }
}
