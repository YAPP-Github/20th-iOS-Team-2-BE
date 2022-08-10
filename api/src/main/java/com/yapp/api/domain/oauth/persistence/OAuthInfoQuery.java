package com.yapp.api.domain.oauth.persistence;

import com.yapp.core.constant.OAuthProvider;
import com.yapp.core.entity.oauth.entity.OAuthInfo;

import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface OAuthInfoQuery {

    Optional<OAuthInfo> findByProviderAndOauthId(OAuthProvider provider, String oauthId);
}
