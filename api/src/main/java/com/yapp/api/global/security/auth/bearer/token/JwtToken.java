package com.yapp.api.global.security.auth.bearer.token;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yapp.realtime.entity.user.entity.element.Authority;

public class JwtToken implements Authentication {
	public static final Authentication ANONYMOUS = anonymous();
	private final UserDetails userDetails;

	public JwtToken(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public static Authentication from(UserDetails userDetails) {
		if (userDetails.isEnabled()) {
			return new JwtToken(userDetails);
		}
		return anonymous(userDetails.getUsername());
	}

	public static Authentication from(String principal) {
		return anonymous(principal);
	}

	@Override
	public String getName() {
		return userDetails.getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userDetails.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return Optional.empty();
	}

	@Override
	public Object getDetails() {
		return userDetails;
	}

	@Override
	public Object getPrincipal() {
		return userDetails.getUsername();
	}

	@Override
	public boolean isAuthenticated() {
		return userDetails.isEnabled();
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {}

	// --

	private static Authentication anonymous() {
		return new AnonymousToken();
	}

	private static Authentication anonymous(String principal) {
		return new AnonymousToken(principal);
	}

	private static class AnonymousToken implements Authentication {
		private static final String SPLITTER = ":";
		private final String PROVIDER;
		private final String OAUTH_ID;

		public AnonymousToken() {
			PROVIDER = "error";
			OAUTH_ID = "";
		}

		public AnonymousToken(String principal) {
			this.PROVIDER = principal.split(SPLITTER)[0];
			this.OAUTH_ID = principal.split(SPLITTER)[1];
		}

		@Override
		public String getName() {
			return PROVIDER + SPLITTER + OAUTH_ID;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return Collections.singleton(new GrantedAuthority() {
				@Override
				public String getAuthority() {
					return Authority.ANONYMOUS.name();
				}
			});
		}

		@Override
		public Object getCredentials() {
			return Optional.empty();
		}

		@Override
		public Object getDetails() {
			return Optional.empty();
		}

		@Override
		public Object getPrincipal() {
			return PROVIDER + SPLITTER + OAUTH_ID;
		}

		@Override
		public boolean isAuthenticated() {
			return false;
		}

		@Override
		public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {}
	}
}
