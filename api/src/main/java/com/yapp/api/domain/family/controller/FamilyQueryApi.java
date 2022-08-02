package com.yapp.api.domain.family.controller;

import static com.yapp.core.constant.ApiConstant.*;
import static org.springframework.http.MediaType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.api.domain.family.controller.dto.FamilyResponse;
import com.yapp.api.domain.family.service.FamilyService;
import com.yapp.core.util.resolver.AuthenticationHasFamily;
import com.yapp.core.persistance.user.entity.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FamilyQueryApi {
	private final FamilyService familyService;

	@GetMapping(value = _FAMILY_HOME, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<FamilyResponse.HomeInfo> retrieveHomeInfo() {
		return ResponseEntity.ok()
							 .build();
	}

	@GetMapping(value = _FAMILY_NOTIFICATIONS, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<FamilyResponse.Notifications> retrieveNotifications() {
		return ResponseEntity.ok()
							 .build();
	}

	@GetMapping(value = "/family")
	ResponseEntity<FamilyResponse.Info> retrieveInfo(@AuthenticationHasFamily User user) {
		return ResponseEntity.ok(familyService.get(user));
	}

	@GetMapping("/family/code")
	ResponseEntity<FamilyResponse.Code> code(@AuthenticationHasFamily User user) {
		return ResponseEntity.ok(FamilyResponse.Code.from(user.getFamily()
															  .getCode()));
	}
}
