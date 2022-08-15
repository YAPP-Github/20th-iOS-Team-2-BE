package com.yapp.api.domain.oauth.controller.dto.request;

import com.yapp.api.domain.oauth.controller.dto.AuthInfoProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    protected String accessToken;
    protected String refreshToken;
    protected String identityToken;
    protected String authorizationCode;
    protected String userId;

    public AuthInfoProvider<KakaoDetail> asKakao() {
        return Kakao.of(accessToken, refreshToken);
    }

    public static class Kakao extends AuthRequest implements AuthInfoProvider<AuthRequest.KakaoDetail> {
        Kakao(String accessToken, String refreshToken) {
            super.accessToken = accessToken;
            super.refreshToken = refreshToken;
        }

        public static AuthInfoProvider<KakaoDetail> of(String accessToken, String refreshToken) {
            return new Kakao(accessToken, refreshToken);
        }

        @Override
        public AuthRequest.KakaoDetail detail() {
            return new AuthRequest.KakaoDetail(accessToken, refreshToken);
        }
    }

    @Getter
    @AllArgsConstructor
    public static class KakaoDetail {
        private String accessToken;
        private String refreshToken;
    }

    public AuthInfoProvider<AppleDetail> asApple() {
        return Apple.of(identityToken, authorizationCode, userId);
    }

    public static class Apple extends AuthRequest implements AuthInfoProvider<AuthRequest.AppleDetail> {
        Apple(String identityToken, String authorizationCode, String userId) {
            super.identityToken = identityToken;
            super.authorizationCode = authorizationCode;
            super.userId = userId;
        }

        public static AuthInfoProvider<AppleDetail> of(String identityToken, String authorizationCode, String userId) {
            return new Apple(identityToken, authorizationCode, userId);
        }

        @Override
        public Apple.AppleDetail detail() {
            return new AuthRequest.AppleDetail(identityToken, authorizationCode, userId);
        }
    }

    @Getter
    @AllArgsConstructor
    public static class AppleDetail {
        private String identityToken;
        private String authorizationCode;
        private String userId;
    }
}

