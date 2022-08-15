package com.yapp.api.global.security.auth.bearer.filter;

import com.yapp.api.global.error.exception.ApiException;
import com.yapp.api.global.security.auth.bearer.token.JwtToken;
import com.yapp.api.global.security.auth.bearer.util.BearerHandler;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.yapp.core.error.exception.ErrorCode.*;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final int START_INDEX = 7;
    private final BearerHandler bearerHandler;
    private final AuthenticationProvider jwtAuthenticationProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        bearerHandler.extractToken(request)
                .ifPresentOrElse(token -> validate(token.substring(START_INDEX), filterChain, request, response), () -> {
                    SecurityContextHolder.getContext()
                            .setAuthentication(JwtToken.ANONYMOUS);
                    try {
                        filterChain.doFilter(request, response);
                    } catch (IOException | ServletException e) {
                        throw new ApiException(NO_AUTHENTICATION_ACCESS);
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new ApiException(OAUTH_KIND_ERROR);
                    }
                });

    }

    private void validate(
            final String token,
            final FilterChain filterChain,
            final HttpServletRequest request,
            final HttpServletResponse response) {
        validateNotEmpty(token);
        try {
            // token form : "provider:oauthId"
            Authentication authenticated = jwtAuthenticationProvider.authenticate(bearerHandler.extractPrincipal(token));
            SecurityContextHolder.getContext()
                    .setAuthentication(authenticated);

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            throw new ApiException(BEARER_TOKEN_EXPIRED);
        } catch (JwtException e) {
            throw new ApiException(BEARER_TOKEN_INVALID);
        } catch (Exception e) {
            throw new ApiException(BEARER_INTERNAL_ERROR);
        }
    }

    private void validateNotEmpty(final String token) {
        if (isNullOrEmpty(token.trim())) {
            throw new ApiException(TOKEN_IS_BLANK);
        }
    }
}
