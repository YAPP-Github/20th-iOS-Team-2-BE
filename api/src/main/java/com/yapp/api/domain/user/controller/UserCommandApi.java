package com.yapp.api.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserCommandApi {
	@DeleteMapping("/history/{messageId}")
	ResponseEntity<Void> removeProfileMessage(@PathVariable(value = "messageId") Long messageId) {
		return ResponseEntity.noContent()
							 .build();
	}

	@DeleteMapping("/user")
	ResponseEntity<Void> removeUser() {
		return ResponseEntity.noContent()
							 .build();
	}

	@PatchMapping("/user")
	ResponseEntity<Void> modifyUser() {
		return ResponseEntity.ok()
							 .build();
	}
}
