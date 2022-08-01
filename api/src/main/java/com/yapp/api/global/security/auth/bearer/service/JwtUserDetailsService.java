package com.yapp.api.global.security.auth.bearer.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.core.error.exception.BaseBusinessException;
import com.yapp.core.error.exception.ErrorCode;
import com.yapp.core.persistance.oauth.entity.OAuthInfo;
import com.yapp.core.persistance.oauth.repo.OAuthInfoRepository;
import com.yapp.core.persistance.user.entity.User;
import com.yapp.core.persistance.user.entity.element.Authority;
import com.yapp.core.persistance.user.handler.UserQueryHandler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class JwtUserDetailsService implements UserDetailsService {
	private static final String SPLITTER = ":";
	private final UserQueryHandler userQueryHandler;
	private final OAuthInfoRepository oAuthInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String provider = username.split(SPLITTER)[0];
		String oauthId = username.split(SPLITTER)[1];

		OAuthInfo oAuthInfo = oAuthInfoRepository.findByProviderAndOauthId(OAuthInfo.OAuthProvider.valueOf(provider), oauthId)
												 .orElseThrow(() -> new BaseBusinessException(ErrorCode.BEARER_TOKEN_INVALID));

		return JwtUserDetails.from(oAuthInfo.getUser());
	}

	public static class JwtUserDetails implements UserDetails {
		@Getter
		private final User user;

		public JwtUserDetails(User user) {
			this.user = user;
		}

		public static UserDetails from(User user) {
			return new JwtUserDetails(user);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return Collections.singleton((GrantedAuthority)() -> user.getAuthority()
																	 .name());
		}

		@Override
		public String getPassword() {
			return null;
		}

		@Override
		public String getUsername() {
			return user.getName();
		}

		@Override
		public boolean isAccountNonExpired() {
			return false;
		}

		@Override
		public boolean isAccountNonLocked() {
			return false;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return false;
		}

		@Override
		public boolean isEnabled() {
			return user.getAuthority() != Authority.ANONYMOUS;
		}
	}
}
