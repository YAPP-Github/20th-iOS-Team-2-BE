package com.yapp.allinone.domain.user.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UserRequest {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @NotBlank(message = "회원 생성 시 이름이 입력되지 않았습니다.")
        private String name;
        @NotBlank(message = "회원 생성 시 역할이 입력되지 않았습니다.")
        private String roleInFamily;
        @NotNull(message = "회원 생성 시 생일이 입력되지 않았습니다.")
        private LocalDate birthDay;
        @NotBlank(message = "회원 생성 시 닉네임이 입력되지 않았습니다.")
        private String nickname;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {
        private String nickname;
        private LocalDate birthDay;
        private String imageLink;
        private String roleInFamily;
    }
}
