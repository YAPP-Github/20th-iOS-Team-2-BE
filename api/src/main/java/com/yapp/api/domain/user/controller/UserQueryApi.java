package com.yapp.api.domain.user.controller;

import static com.yapp.core.constant.ApiConstant.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.api.domain.user.controller.dto.ProfileResponse;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.api.domain.user.service.UserService;
import com.yapp.api.global.security.auth.resolver.MustAuthenticated;
import com.yapp.api.global.security.auth.bearer.token.JwtToken;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserQueryApi {
	private final UserService userService;

	@GetMapping("/test")
	ResponseEntity<Object> test(@MustAuthenticated User user) {
		return ResponseEntity.ok(user);
	}

	@GetMapping("/user/simple")
	ResponseEntity<ProfileResponse.UserSimple> retrieveUser(@MustAuthenticated User user) {
		return ResponseEntity.ok(userService.getSimple(user));
	}

	@GetMapping(_USER_HISTORY)
	ResponseEntity<ProfileResponse.MessageHistory> retrieveMessageHistory(@PathVariable(value = USER_ID) Long userId) {
		return ResponseEntity.ok()
							 .build();
	}

	@GetMapping("/user/detail")
	ResponseEntity<ProfileResponse.UserDetail> retreiveUserDetail(@MustAuthenticated User user) {
		return ResponseEntity.ok(userService.getDetail(user));
	}
}
