package com.yapp.api.domain.family.controller;

import static org.springframework.http.MediaType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.api.domain.family.controller.dto.FamilyRequest;
import com.yapp.api.domain.family.controller.dto.FamilyResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FamilyCommandApi {
	@PostMapping(value = "/family", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<FamilyResponse.Create> createFamily(@RequestBody FamilyRequest.Create request) {
		return null;
	}

	@PatchMapping(value = "/family", consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modifyFamilyInfo(@RequestBody FamilyRequest.Modify request) {
		return ResponseEntity.ok()
							 .build();
	}

	@DeleteMapping(value = "/family/{familyId}")
	ResponseEntity<Void> removeFamily(@PathVariable(value = "familyId") Long familyId) {
		return ResponseEntity.noContent()
							 .build();
	}

	@PostMapping(value = "/family/greeting/message", consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> createGreetingWithMessage(@RequestBody FamilyRequest.GreetWithMessage request) {
		return ResponseEntity.ok()
							 .build();
	}

	@PostMapping(value = "/family/greeting/emoji", consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> createGreetingWithEmoji(@RequestBody FamilyRequest.GreetWithEmoji request) {
		return ResponseEntity.ok()
							 .build();
	}

}

