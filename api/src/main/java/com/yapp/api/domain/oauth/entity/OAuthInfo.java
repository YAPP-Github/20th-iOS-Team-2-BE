package com.yapp.api.domain.oauth.entity;

import static com.yapp.api.domain.oauth.entity.OAuthInfo.OauthProvider.*;
import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.yapp.api.domain.user.persistence.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class OAuthInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(STRING)
	private OauthProvider provider;
	private String oauthId;

	@ManyToOne(fetch = LAZY)
	private User user;

	private OAuthInfo(OauthProvider provider, String oauthId) {
		this.provider = provider;
		this.oauthId = oauthId;
	}

	public static OAuthInfo of(String providerKind, String oauthId) {
		if (isKakao(providerKind) && !oauthId.isBlank()) {
			return new OAuthInfo(KAKAO, oauthId);
		}

		if (isApple(providerKind) && !oauthId.isBlank()) {
			return new OAuthInfo(APPLE, oauthId);
		}

		return new INVALID();
	}

	@Getter
	@AllArgsConstructor
	enum OauthProvider {
		KAKAO("kakao"), APPLE("apple"), NULL("");

		public static boolean isKakao(String providerKind) {
			return KAKAO.value.equalsIgnoreCase(providerKind);
		}

		public static boolean isApple(String providerKind) {
			return APPLE.value.equalsIgnoreCase(providerKind);
		}

		private final String value;
	}

	private static class INVALID extends OAuthInfo {
		@Override
		public OauthProvider getProvider() {
			return NULL;
		}
	}
}
