package com.yapp.api.domain.user.controller;

import static com.yapp.core.constant.ApiConstant.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.api.domain.user.controller.dto.ProfileResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserQueryApi {
	@GetMapping(_USER_HISTORY)
	ResponseEntity<ProfileResponse.MessageHistory> retrieveMessageHistory(@PathVariable(value = USER_ID) Long userId) {
		return ResponseEntity.ok()
							 .build();
	}
}
