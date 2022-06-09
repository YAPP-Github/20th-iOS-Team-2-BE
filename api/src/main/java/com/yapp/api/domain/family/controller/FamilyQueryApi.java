package com.yapp.api.domain.family.controller;

import static org.springframework.http.MediaType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.api.domain.family.controller.dto.FamilyResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FamilyQueryApi {
	@GetMapping(value = "/family/home", produces = APPLICATION_JSON_VALUE)
	ResponseEntity<FamilyResponse.HomeInfo> retrieveHomeInfo() {
		return ResponseEntity.ok()
							 .build();
	}

	@GetMapping(value = "/family/contifications", produces = APPLICATION_JSON_VALUE)
	ResponseEntity<FamilyResponse.Notifications> retrieveNotifications() {
		return ResponseEntity.ok()
							 .build();
	}

}
