package com.yapp.api.domain.user.persistence.entity.element;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

import java.util.Set;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

import com.yapp.api.domain.oauth.entity.OAuthInfo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PUBLIC)
public class OAuthInfos {
	@OneToMany(mappedBy = "user", fetch = LAZY, cascade = ALL, orphanRemoval = true)
	private Set<OAuthInfo> oAuthInfos;

	public boolean contains(OAuthInfo oAuthInfo) {
		return oAuthInfos.contains(oAuthInfo);
	}
}
