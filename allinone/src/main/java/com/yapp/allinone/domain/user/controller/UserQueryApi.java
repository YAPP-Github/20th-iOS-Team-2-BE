package com.yapp.allinone.domain.user.controller;

import com.yapp.allinone.common.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.allinone.common.security.auth.resolver.MustAuthenticated;
import com.yapp.allinone.domain.user.controller.model.ProfileResponse;
import com.yapp.allinone.domain.user.service.UserService;
import com.yapp.supporter.entity.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserQueryApi {
    private final UserService userService;

    @GetMapping("/user/simple")
    ResponseEntity<ProfileResponse.UserSimple> retrieveUser(@MustAuthenticated User user) {
        return ResponseEntity.ok(userService.getSimple(user));
    }

    @GetMapping("/user/{userId}/history")
    ResponseEntity<ProfileResponse.MessageHistory> retrieveMessageHistory(
            @AuthenticationHasFamily User orderedUser,
            @PathVariable("userId") Long targetUserId) {
        return ResponseEntity.ok(userService.history(orderedUser, targetUserId));

    }

    @GetMapping("/user/detail")
    ResponseEntity<ProfileResponse.UserDetail> retreiveUserDetail(@MustAuthenticated User user) {
        return ResponseEntity.ok(userService.getDetail(user));
    }
}
