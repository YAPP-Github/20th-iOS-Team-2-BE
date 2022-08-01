package com.yapp.core.persistance.user.entity.element;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

import com.yapp.core.error.exception.BaseBusinessException;
import com.yapp.core.error.exception.ErrorCode;
import com.yapp.core.persistance.oauth.entity.OAuthInfo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PUBLIC)
public class OAuthInfos {
	@OneToMany(mappedBy = "user", fetch = LAZY, cascade = ALL, orphanRemoval = true)
	private Set<OAuthInfo> oAuthInfos = new HashSet<>();

	public boolean contains(OAuthInfo oAuthInfo) {
		return oAuthInfos.contains(oAuthInfo);
	}

	public OAuthInfo getOAuthInfo(OAuthInfo.OAuthProvider provider) {
		return oAuthInfos.stream()
						 .filter(info -> info.getProvider() == provider)
						 .findFirst()
						 .orElseThrow(() -> new BaseBusinessException(ErrorCode.OAUTH_ERROR));
	}
}
