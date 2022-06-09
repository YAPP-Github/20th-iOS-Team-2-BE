package com.yapp.api.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.api.domain.user.controller.dto.ProfileResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserQueryApi {
	@GetMapping("/history/{userId}")
	ResponseEntity<ProfileResponse.MessageHistory> retrieveMessageHistory(@PathVariable(value = "userId") Long userId) {
		return ResponseEntity.ok()
							 .build();
	}
}
