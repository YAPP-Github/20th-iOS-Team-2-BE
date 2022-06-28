package com.yapp.api.global.security.auth.bearer.util;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.yapp.api.global.security.auth.bearer.token.JwtToken;

import io.jsonwebtoken.Jwts;

@Component
public class BearerHandler {
	private final String TOKEN_KEY;
	private final String SECRET_KEY;

	public BearerHandler(@Value("security.jwt.token-key") String TOKEN_KEY,
						 @Value("security.jwt.secret-key") String SECRET_KEY) {
		this.TOKEN_KEY = TOKEN_KEY;
		this.SECRET_KEY = SECRET_KEY;
	}

	public Optional<String> extractToken(HttpServletRequest request) {
		return Optional.ofNullable(request.getHeader(TOKEN_KEY));
	}

	public Authentication extractPrincipal(String token) {
		return JwtToken.from(Jwts.parser()
								 .setSigningKey(SECRET_KEY)
								 .parseClaimsJws(token)
								 .getBody()
								 .getSubject());
	}
}
