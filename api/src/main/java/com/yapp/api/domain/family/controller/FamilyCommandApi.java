package com.yapp.api.domain.family.controller;

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

import com.yapp.api.domain.family.controller.dto.FamilyRequest;
import com.yapp.api.domain.family.controller.dto.FamilyResponse;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.family.service.FamilyService;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.api.global.security.auth.resolver.MustAuthenticated;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FamilyCommandApi {
	private final FamilyService familyService;

	// Sync
	@PostMapping(value = _FAMILY, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<FamilyResponse.Create> createFamily(@MustAuthenticated User user,
													   @RequestBody FamilyRequest.Create request) {
		Family createdFamily = familyService.create(user, request.getFamilyName(), request.getFamilyMotto());
		return ResponseEntity.ok(new FamilyResponse.Create(createdFamily.getId()));
	}

	// Async
	@PatchMapping(value = _FAMILY, consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modifyFamilyInfo(@MustAuthenticated User user, @RequestBody FamilyRequest.Modify request) {
		CompletableFuture.runAsync(() -> familyService.modify(user,
															  request.getImageLink(),
															  request.getFamilyName(),
															  request.getFamilyMotto()));
		return ResponseEntity.ok()
							 .build();
	}

	@DeleteMapping(value = _FAMILY_RESOURCE)
	ResponseEntity<Void> removeFamily(@PathVariable(value = FAMILY_ID) Long familyId) {
		return ResponseEntity.noContent()
							 .build();
	}

	@PostMapping(value = _FAMILY_GREETING_MESSAGE, consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> createGreetingWithMessage(@RequestBody FamilyRequest.GreetWithMessage request) {
		return ResponseEntity.ok()
							 .build();
	}

	@PostMapping(value = _FAMILY_GREETING_EMOJI, consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> createGreetingWithEmoji(@RequestBody FamilyRequest.GreetWithEmoji request) {
		return ResponseEntity.ok()
							 .build();
	}

}

