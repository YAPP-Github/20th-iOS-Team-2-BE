package com.yapp.api.domain.user.controller;

import com.yapp.api.domain.user.controller.model.UserRequest;
import com.yapp.api.domain.user.service.UserService;
import com.yapp.api.global.error.exception.ApiException;
import com.yapp.api.global.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.api.global.security.auth.resolver.MustAuthenticated;
import com.yapp.realtime.entity.user.entity.User;
import com.yapp.realtime.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.yapp.realtime.constant.ApiConstant.MESSAGE_ID;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserCommandApi {
    private final UserService userService;

    @PostMapping(value = "/user", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createUser(
            @MustAuthenticated User user,
            @Valid @RequestBody UserRequest.Create request) {
        if (user.isEmpty()) {
            userService.create(user, request.getName(), request.getNickname(), request.getRoleInFamily(), request.getBirthDay());

            return ResponseEntity.ok()
                    .build();
        }

        throw new ApiException(ErrorCode.ALREADY_JOINED_USER);
    }

    @DeleteMapping("/user/history/{messageId}")
    ResponseEntity<Void> removeProfileMessage(
            @AuthenticationHasFamily User user,
            @PathVariable(value = MESSAGE_ID) Long messageId) {
        userService.removeHistory(user, messageId);
        return ResponseEntity.ok()
                .build();
    }

    @Deprecated
    @DeleteMapping("/user")
    ResponseEntity<Void> removeUser() {
        return ResponseEntity.noContent()
                .build();
    }

    @PatchMapping("/user")
    ResponseEntity<Void> modifyUser(@MustAuthenticated User user, @RequestBody UserRequest.Modify request) {
        userService.modify(user, request.getNickname(), request.getImageLink(), request.getBirthDay(), request.getRoleInFamily());

        return ResponseEntity.ok()
                .build();
    }
}
