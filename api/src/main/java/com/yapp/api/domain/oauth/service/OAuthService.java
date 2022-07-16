package com.yapp.api.domain.oauth.service;

import static com.yapp.core.error.exception.ErrorCode.*;
import static org.springframework.http.HttpMethod.*;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.yapp.api.domain.oauth.entity.OAuthInfo;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.api.domain.user.persistence.handler.UserCommandHandler;
import com.yapp.api.domain.user.persistence.handler.UserQueryHandler;
import com.yapp.api.global.security.auth.bearer.util.BearerHandler;
import com.yapp.core.error.exception.BaseBusinessException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
public class OAuthService {
	private static final String KAKAO = "KAKAO";
	private static final String AUTHORIZATION = "Authorization";
	private static final String CONTENT_TYPE = "Content-type";
	private static final String PREFIX = "Bearer ";
	private static final URI KAKAO_AUTH_URI = URI.create("https://kapi.kakao.com/v2/user/me");
	private static final String LOGIN = "login";
	private static final String JOIN = "join";
	private static final String SPLITTER = ";";

	private final UserQueryHandler userQueryHandler;
	private final UserCommandHandler userCommandHandler;
	private final RestTemplate restTemplate;
	private final BearerHandler bearerHandler;

	public String auth(String kind, String accessToken, String refreshToken) {
		// refreshToken not use yet

		// 1. oauth
		OAuthResponse restResult = restForAuth(kind, accessToken);

		// 2. restResult 를 바탕으로 디비에 회원조회 -> 토큰 생성
		User foundUser = userQueryHandler.findOne(userRepository -> userRepository.findByProviderAndOauthId(restResult.getProvider(),
																											restResult.getIdAsString()))
										 .orElseGet(() -> {
											 if (restResult.getProvider()
														   .equals(KAKAO)) {
												 User newUser = new User("",
																		 null,
																		 null,
																		 OAuthInfo.of(KAKAO,
																					  restResult.getIdAsString()));
												 userCommandHandler.save(userRepository -> userRepository.save(newUser));
												 return newUser;
											 }
											 return new User.ANONYMOUS();
										 });
		// 없다면 ? type : join
		if (foundUser.getName()
					 .isBlank()) {
			return JOIN + SPLITTER + bearerHandler.create(restResult.combinedInfo());
		}
		// 있다면 ? type : login
		return LOGIN + SPLITTER + bearerHandler.create(restResult.combinedInfo());
	}

	private OAuthResponse restForAuth(String kind, String oauthToken) {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(AUTHORIZATION, bearerToken(oauthToken));
		httpHeaders.set(CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
		try {
			if (KAKAO.equals(kind.toUpperCase())) {
				OAuthResponse.FromKakao result = restTemplate.exchange(KAKAO_AUTH_URI,
																	   GET,
																	   new HttpEntity(httpHeaders),
																	   OAuthResponse.FromKakao.class)
															 .getBody();
				assert result != null;
				result.setProvider(KAKAO);
				return result;
			}
			throw new BaseBusinessException(OAUTH_KIND_ERROR,
											new RuntimeException(
												"OAuth Error : which occerred from Auth, Not valid kind"));
		} catch (HttpClientErrorException e) {
			throw new BaseBusinessException(OAUTH_ERROR,
											new RuntimeException("OAuth Error : which occerred from KAKAO"));
		}
	}

	private String bearerToken(String token) {
		return PREFIX + token;
	}

	@NoArgsConstructor
	@AllArgsConstructor
	private static class OAuthResponse {
		private Long id;
		@Setter
		private String provider;

		@Getter
		@Setter
		@NoArgsConstructor
		@AllArgsConstructor
		public static class FromKakao extends OAuthResponse {
			private Info kakao_account;

			@Getter
			@Setter
			@NoArgsConstructor
			@AllArgsConstructor
			public static class Info {
				private boolean profile_needs_agreement;
				private boolean profile_nickname_needs_agreement;
				private boolean profile_image_needs_agreement;
				private Profile profile;

				@Getter
				@Setter
				@NoArgsConstructor
				@AllArgsConstructor
				public static class Profile {
					private String nickname;
					private String thumbnail_image_url;
					private String profile_image_url;
					private boolean is_default_image;
				}
			}
		}

		public String combinedInfo() {
			return provider + ":" + id;
		}

		public String getIdAsString() {
			return String.valueOf(id);
		}

		public String getProvider() {
			return provider.toUpperCase();
		}
	}
}
