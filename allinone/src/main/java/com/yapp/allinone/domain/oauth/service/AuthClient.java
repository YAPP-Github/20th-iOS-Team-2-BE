package com.yapp.allinone.domain.oauth.service;

import com.yapp.allinone.common.exception.ApiException;
import com.yapp.allinone.domain.oauth.controller.dto.AuthInfoProvider;
import com.yapp.allinone.domain.oauth.controller.dto.internal.OAuthResponse;
import com.yapp.allinone.domain.oauth.controller.dto.request.AuthRequest;
import com.yapp.supporter.error.exception.ExceptionThrowableLayer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.yapp.supporter.error.exception.ErrorCode.OAUTH_ERROR;
import static org.springframework.http.HttpMethod.GET;

/**
 * Author : daehwan2yo
 * Date : 2022/07/17
 * Info :
 **/
@Component
@RequiredArgsConstructor
public class AuthClient implements ExceptionThrowableLayer {
    private final String KAKAO = "KAKAO";
    private final String APPLE = "APPLE";
    private final URI KAKAO_AUTH_URI = URI.create("https://kapi.kakao.com/v2/user/me");
    private final URI APPLE_VERIFICATION_URI = URI.create("https://appleid.apple.com/auth/keys");

    private final RestTemplate restTemplate;

    public OAuthResponse request(String kind, AuthRequest authRequest) {
        if (KAKAO.equals(kind)) {
            return requestForKakao(authRequest.asKakao());
        }
        if (APPLE.equals(kind)) {
            return requestForApple(authRequest.asApple());
        }
        return new OAuthResponse.EMPTY();
    }

    private OAuthResponse requestForKakao(AuthInfoProvider<AuthRequest.KakaoDetail> secureDetail) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(secureDetail.detail()
                .getAccessToken());
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        try {
            OAuthResponse.FromKakao result = restTemplate.exchange(KAKAO_AUTH_URI, GET, new HttpEntity(httpHeaders), OAuthResponse.FromKakao.class)
                    .getBody();
            assert result != null;
            result.setProvider(KAKAO);
            return result;

        } catch (HttpClientErrorException e) {
            throw new ApiException(OAUTH_ERROR, packageName(this.getClass()));
        }
    }

    private OAuthResponse requestForApple(AuthInfoProvider<AuthRequest.AppleDetail> secureDetail) {
        try {
            OAuthResponse.FromApple result = restTemplate.getForObject(APPLE_VERIFICATION_URI, OAuthResponse.FromApple.class);
            if (result == null) {
                throw new ApiException(OAUTH_ERROR, packageName(this.getClass()));
            }
            result.setToken(secureDetail.detail()
                    .getIdentityToken());
            result.setId(secureDetail.detail()
                    .getUserId());
            result.setProvider(APPLE);
            return result;

        } catch (HttpClientErrorException e) {
            throw new ApiException(OAUTH_ERROR, packageName(this.getClass()));
        }
    }
}
