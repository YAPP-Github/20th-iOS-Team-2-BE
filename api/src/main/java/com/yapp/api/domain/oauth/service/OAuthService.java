package com.yapp.api.domain.oauth.service;

import org.springframework.stereotype.Service;

import com.yapp.api.domain.oauth.controller.dto.internal.OAuthResponse;
import com.yapp.api.domain.oauth.controller.dto.request.AuthRequest;
import com.yapp.api.domain.oauth.entity.OAuthInfo;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.api.domain.user.persistence.handler.UserCommandHandler;
import com.yapp.api.domain.user.persistence.handler.UserQueryHandler;
import com.yapp.api.global.security.auth.bearer.util.BearerHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuthService {
	private static final String KAKAO = "KAKAO";
	private static final String APPLE = "APPLE";
	private static final String LOGIN = "login";
	private static final String JOIN = "join";
	private static final String SPLITTER = ";";

	private final UserQueryHandler userQueryHandler;
	private final UserCommandHandler userCommandHandler;
	private final BearerHandler bearerHandler;
	private final AuthClient authClient;

	public String auth(String kind, AuthRequest authRequest) {
		// refreshToken not use yet

		// 1. oauth
		OAuthResponse authResult = authClient.request(kind, authRequest);

		// 2. restResult 를 바탕으로 디비에 회원조회 -> 토큰 생성
		User foundUser = userQueryHandler.findOne(userRepository -> userRepository.findByProviderAndOauthId(authResult.getProvider(),
																											authResult.getId()))
										 .orElseGet(() -> {
											 if (authResult.getProvider()
														   .equals(KAKAO)) {
												 User newUser = new User("",
																		 null,
																		 null,
																		 OAuthInfo.of(KAKAO, authResult.getId()));
												 userCommandHandler.save(userRepository -> userRepository.save(newUser));
												 return newUser;
											 }
											 return new User.ANONYMOUS();
										 });
		// 없다면 ? type : join
		if (foundUser.getName()
					 .isBlank()) {
			return JOIN + SPLITTER + bearerHandler.create(authResult.combinedInfo());
		}
		// 있다면 ? type : login
		return LOGIN + SPLITTER + bearerHandler.create(authResult.combinedInfo());
	}
}
