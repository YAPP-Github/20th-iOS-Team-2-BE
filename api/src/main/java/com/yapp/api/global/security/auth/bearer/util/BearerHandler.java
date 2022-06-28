package com.yapp.api.global.security.auth.bearer.util;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.yapp.api.global.security.auth.bearer.token.JwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class BearerHandler {
	private final String TOKEN_KEY;
	private final String SECRET_KEY;
	private final int VALID_TIME;

	public BearerHandler(@Value("security.jwt.token-key") String TOKEN_KEY,
						 @Value("security.jwt.secret-key") String SECRET_KEY,
						 @Value("security.jwt.valid-time") String VALID_TIME) {
		this.TOKEN_KEY = TOKEN_KEY;
		this.SECRET_KEY = SECRET_KEY;
		this.VALID_TIME = Integer.parseInt("3600");
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

	public String create(String combinedInfo) {
		Long validTimeMilli = VALID_TIME * 1000L;

		Date now = new Date();
		Claims claims = Jwts.claims()
							.setSubject(combinedInfo);

		return Jwts.builder()
				   .setClaims(claims)
				   .setIssuedAt(now)
				   .setExpiration(new Date(now.getTime() + validTimeMilli))
				   .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				   .compact();
	}

/*	public String createToken(String principal) {
		OAuthInfo oauthInfo = (OauthInfo)principle;


	}*/
}
