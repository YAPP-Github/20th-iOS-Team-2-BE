package com.yapp.api.domain.oauth.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthResponse {
	private static final String SPLITTER = ";";
	private String type;
	private String authToken;
	private Long userId;

	// tokenInfo form : "type;accessToken"
	private static String extractAccessToken(String tokenInfo) {
		return tokenInfo.split(SPLITTER)[1];
	}

	private static String extractType(String tokenInfo) {
		return tokenInfo.split(SPLITTER)[0];
	}

	public static AuthResponse of(String tokenInfo, Long userId) {
		return new AuthResponse(extractType(tokenInfo), extractAccessToken(tokenInfo), userId);
	}

	public static class FromKakao {

	}
}
