package com.yapp.api.domain.oauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.api.domain.oauth.controller.dto.AuthRequest;
import com.yapp.api.domain.oauth.controller.dto.AuthResponse;
import com.yapp.api.domain.oauth.service.OAuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthApi {
	private final OAuthService oAuthService;

	@GetMapping("/auth")
	public ResponseEntity auth(@RequestParam(name = "kind") String kind, @RequestBody AuthRequest request) {
		return ResponseEntity.ok(AuthResponse.from(oAuthService.auth(kind, request.getOauthToken())));
	}
}
