package com.yapp.allinone.common.security.auth.bearer.service;

import com.yapp.allinone.common.security.auth.bearer.token.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        if (userDetails.isEnabled()) {
            return JwtToken.from(userDetails);
        }
        return JwtToken.ANONYMOUS;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
