package com.yapp.api.domain.user.controller;

import static com.yapp.core.constant.ApiConstant.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserCommandApi {
	@DeleteMapping(_USER_HISTORY_RESOURCE)
	ResponseEntity<Void> removeProfileMessage(@PathVariable(value = MESSAGE_ID) Long messageId) {
		return ResponseEntity.noContent()
							 .build();
	}

	@DeleteMapping(_USER)
	ResponseEntity<Void> removeUser() {
		return ResponseEntity.noContent()
							 .build();
	}

	@PatchMapping(_USER)
	ResponseEntity<Void> modifyUser() {
		return ResponseEntity.ok()
							 .build();
	}
}
