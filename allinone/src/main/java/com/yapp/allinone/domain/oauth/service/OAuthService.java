package com.yapp.allinone.domain.oauth.service;

import com.yapp.allinone.common.security.auth.bearer.util.BearerHandler;
import com.yapp.allinone.domain.oauth.controller.dto.AuthResponse;
import com.yapp.allinone.domain.oauth.controller.dto.internal.OAuthResponse;
import com.yapp.allinone.domain.oauth.controller.dto.request.AuthRequest;
import com.yapp.allinone.domain.oauth.persistence.query.handler.OAuthInfoQueryHandler;
import com.yapp.allinone.domain.user.persistence.command.handler.UserCommandHandler;
import com.yapp.supporter.constant.OAuthProvider;
import com.yapp.supporter.entity.oauth.entity.OAuthInfo;
import com.yapp.supporter.entity.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private static final String KAKAO = "KAKAO";
    private static final String APPLE = "APPLE";
    private static final String LOGIN = "login";
    private static final String JOIN = "join";
    private static final String SPLITTER = ";";

    private final UserCommandHandler userCommandHandler;
    private final BearerHandler bearerHandler;
    private final AuthClient authClient;
    private final OAuthInfoQueryHandler oAuthInfoQueryHandler;

    @Transactional
    public AuthResponse auth(String kind, AuthRequest authRequest) {
        // refreshToken not use yet

        // 1. oauth
        OAuthResponse authResult = authClient.request(kind.toUpperCase(), authRequest);

        // 2. restResult 를 바탕으로 디비에 회원조회 -> 토큰 생성
        OAuthInfo oAuthInfo = oAuthInfoQueryHandler.findOne(OAuthProvider.valueOf(authResult.getProvider()), authResult.getId())
                .orElseGet(() -> freshUser(authResult));

        User foundUser = oAuthInfo.getUser();

        // 없다면 ? type : join
        if (foundUser.getName().isBlank()) {
            return AuthResponse.of(JOIN + SPLITTER + bearerHandler.create(authResult.combinedInfo()), foundUser.getId());
        }
        // 있다면 ? type : login
        return AuthResponse.of(LOGIN + SPLITTER + bearerHandler.create(authResult.combinedInfo()), foundUser.getId());
    }

    private OAuthInfo freshUser(OAuthResponse authResult) {
        if (OAuthProvider.isKakao(authResult.getProvider())) {
            return saveUser(authResult.getId(), OAuthProvider.KAKAO).getOAuthInfos()
                    .getOAuthInfo(OAuthProvider.KAKAO);
        }

        if (OAuthProvider.isApple(authResult.getProvider())) {
            return saveUser(authResult.getId(), OAuthProvider.APPLE).getOAuthInfos()
                    .getOAuthInfo(OAuthProvider.APPLE);
        }
        return null;
    }

    private User saveUser(String oauthId, OAuthProvider provider) {
        User newUser = User.normalUser("", null, null, OAuthInfo.of(provider, oauthId));
        userCommandHandler.save(newUser);

        return newUser;
    }
}
