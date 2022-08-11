package com.yapp.api.domain.oauth.persistence.query.handler.jpa;

import com.yapp.api.domain.oauth.persistence.query.handler.OAuthInfoQueryHandler;
import com.yapp.api.domain.oauth.persistence.query.repository.OAuthInfoQuery;
import com.yapp.core.constant.OAuthProvider;
import com.yapp.core.entity.oauth.entity.OAuthInfo;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class OAuthInfoQueryJpaHandler implements OAuthInfoQueryHandler {
    private final OAuthInfoQuery oAuthInfoQuery;

    public OAuthInfoQueryJpaHandler(OAuthInfoQuery oAuthInfoQuery) {
        this.oAuthInfoQuery = oAuthInfoQuery;
    }

    @Override
    public Optional<OAuthInfo> findOne(
            OAuthProvider provider, String oauthId) {
        return Optional.empty();
    }
}
