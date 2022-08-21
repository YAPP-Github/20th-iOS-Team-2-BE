package com.yapp.api.domain.oauth.persistence.query.handler;

import com.yapp.realtime.constant.OAuthProvider;
import com.yapp.realtime.entity.oauth.entity.OAuthInfo;

import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface OAuthInfoQueryHandler {

    Optional<OAuthInfo> findOne(OAuthProvider provider, String oauthId);
}
