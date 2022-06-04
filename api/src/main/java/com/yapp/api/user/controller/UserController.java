package com.yapp.api.user.controller;

import com.yapp.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/oauth/kakao/login")
    public void loginWithKakao() {
        userService.registerWithKakao();
    }

    @GetMapping("/oauth/apple/login")
    public void loginWithApple() {

    }
}
