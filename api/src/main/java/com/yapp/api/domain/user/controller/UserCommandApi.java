package com.yapp.api.domain.user.controller;

import static com.yapp.core.constant.ApiConstant.*;
import static org.springframework.http.MediaType.*;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.api.domain.user.controller.dto.UserRequest;
import com.yapp.api.global.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.core.error.exception.BaseBusinessException;
import com.yapp.core.error.exception.ErrorCode;
import com.yapp.core.persistance.user.entity.User;
import com.yapp.api.domain.user.service.UserService;
import com.yapp.api.global.security.auth.resolver.MustAuthenticated;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserCommandApi {
	private final UserService userService;

	@PostMapping(value = _USER, consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> createUser(@MustAuthenticated User user, @RequestBody UserRequest.Create request) {

		if(user.isEmpty()) {
			CompletableFuture.runAsync(() -> userService.create(user,
																request.getName(),
																request.getNickname(),
																request.getRoleInFamily(),
																request.getBirthDay()))
							 .exceptionally(throwable -> {
								 log.error("[ERROR] {}", throwable.getMessage());
								 return null;
							 });

			return ResponseEntity.ok()
								 .build();
		}

		throw new BaseBusinessException(ErrorCode.ALREADY_JOINED_USER);
	}

	@DeleteMapping(_USER_HISTORY_RESOURCE)
	ResponseEntity<Void> removeProfileMessage(@AuthenticationHasFamily User user,
											  @PathVariable(value = MESSAGE_ID) Long messageId) {
		userService.removeHistory(user, messageId);
		return ResponseEntity.ok()
							 .build();
	}

	@DeleteMapping(_USER)
	ResponseEntity<Void> removeUser() {
		return ResponseEntity.noContent()
							 .build();
	}

	@PatchMapping(_USER)
	ResponseEntity<Void> modifyUser(@MustAuthenticated User user, @RequestBody UserRequest.Modify request) {
		CompletableFuture.runAsync(() -> userService.modify(user,
															request.getNickname(),
															request.getImageLink(),
															request.getBirthDay(),
															request.getRoleInFamily()))
						 .exceptionally(throwable -> {
							 log.error("[ERROR] {}", throwable.getMessage());
							 return null;
						 });

		return ResponseEntity.ok()
							 .build();
	}
}
