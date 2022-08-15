package com.yapp.api.domain.oauth.controller;

import com.yapp.api.domain.oauth.controller.dto.request.AuthRequest;
import com.yapp.api.domain.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthApi {
    private final OAuthService oAuthService;

    @PostMapping("/auth")
    public ResponseEntity auth(@RequestParam(name = "kind") String kind, @RequestBody AuthRequest request) {
        return ResponseEntity.ok(oAuthService.auth(kind, request));
    }
}
