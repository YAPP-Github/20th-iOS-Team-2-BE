package com.yapp.allinone.domain.family.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class FamilyRequest {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @NotBlank(message = "가족 이름이 입력되지 않았습니다.")
        private String familyName;
        @NotBlank(message = "가훈이 입력되지 않았습니다.")
        private String familyMotto;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {
        private String familyName;
        private String familyMotto;
        private List<@Valid Nickname> nicknames = new ArrayList<>();

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        @Setter
        public class Nickname {
            @NotBlank(message = "닉네임 변경 대상의 id가 입력되지 않았습니다.")
            private Long userId;
            @NotBlank(message = "변경될 닉네임 입력이 되지 않았습니다.")
            private String newNickname;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GreetWithMessage {
        private String content;
    }
}
