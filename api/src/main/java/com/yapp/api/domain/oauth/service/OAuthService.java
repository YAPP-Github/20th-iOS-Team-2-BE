package com.yapp.api.domain.oauth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.api.domain.oauth.controller.dto.AuthResponse;
import com.yapp.api.domain.oauth.controller.dto.internal.OAuthResponse;
import com.yapp.api.domain.oauth.controller.dto.request.AuthRequest;
import com.yapp.api.global.security.auth.bearer.util.BearerHandler;
import com.yapp.core.persistence.oauth.entity.OAuthInfo;
import com.yapp.core.persistence.oauth.repo.OAuthInfoRepository;
import com.yapp.core.persistence.user.entity.User;
import com.yapp.core.persistence.user.handler.user.UserCommandHandler;
import com.yapp.core.persistence.user.handler.user.UserQueryHandler;

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
	private final OAuthInfoRepository oAuthInfoRepository;

	@Transactional
	public AuthResponse auth(String kind, AuthRequest authRequest) {
		// refreshToken not use yet

		// 1. oauth
		OAuthResponse authResult = authClient.request(kind.toUpperCase(), authRequest);

		// 2. restResult 를 바탕으로 디비에 회원조회 -> 토큰 생성
		OAuthInfo oAuthInfo = oAuthInfoRepository.findByProviderAndOauthId(OAuthInfo.OAuthProvider.valueOf(authResult.getProvider()),
																		   authResult.getId())
												 .orElseGet(() -> freshUser(authResult));

		User foundUser = oAuthInfo.getUser();

		// 없다면 ? type : join
		if (foundUser.getName()
					 .isBlank()) {
			return AuthResponse.of(JOIN + SPLITTER + bearerHandler.create(authResult.combinedInfo()),
								   foundUser.getId());
		}
		// 있다면 ? type : login
		return AuthResponse.of(LOGIN + SPLITTER + bearerHandler.create(authResult.combinedInfo()), foundUser.getId());
	}

	private OAuthInfo freshUser(OAuthResponse authResult) {
		if (authResult.isKind(KAKAO)) {
			return saveUser(authResult, KAKAO).getOAuthInfos()
											  .getOAuthInfo(OAuthInfo.OAuthProvider.KAKAO);
		}

		if (authResult.isKind(APPLE)) {
			return saveUser(authResult, APPLE).getOAuthInfos()
											  .getOAuthInfo(OAuthInfo.OAuthProvider.APPLE);
		}
		return null;
	}

	private User saveUser(OAuthResponse authResult, String apple) {
		User newUser = new User("", null, null, OAuthInfo.of(apple, authResult.getId()));
		userCommandHandler.create(userRepository -> userRepository.save(newUser));
		return newUser;
	}
}
