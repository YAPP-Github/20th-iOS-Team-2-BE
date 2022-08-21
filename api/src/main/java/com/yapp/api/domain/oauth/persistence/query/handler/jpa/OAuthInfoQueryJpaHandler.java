package com.yapp.api.domain.oauth.persistence.query.handler.jpa;

import com.yapp.api.domain.oauth.persistence.query.handler.OAuthInfoQueryHandler;
import com.yapp.api.domain.oauth.persistence.repository.OAuthInfoJpaRepository;
import com.yapp.realtime.constant.OAuthProvider;
import com.yapp.realtime.entity.oauth.entity.OAuthInfo;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class OAuthInfoQueryJpaHandler implements OAuthInfoQueryHandler {
    private final OAuthInfoJpaRepository oAuthInfoQuery;

    public OAuthInfoQueryJpaHandler(OAuthInfoJpaRepository oAuthInfoQuery) {
        this.oAuthInfoQuery = oAuthInfoQuery;
    }

    @Override
    public Optional<OAuthInfo> findOne(OAuthProvider provider, String oauthId) {
        return oAuthInfoQuery.findByOauthIdAndProvider(oauthId, provider);
    }
}
