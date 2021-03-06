package com.yapp.core.persistance.oauth.entity;

import static com.yapp.core.persistance.oauth.entity.OAuthInfo.OAuthProvider.*;
import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yapp.core.persistance.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "oauth_info")
public class OAuthInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(STRING)
	private OAuthProvider provider;
	private String oauthId;

	@Setter
	@ManyToOne
	private User user;

	private OAuthInfo(OAuthProvider provider, String oauthId) {
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
	public enum OAuthProvider {
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
		public OAuthProvider getProvider() {
			return NULL;
		}
	}
}
