package com.yapp.api.domain.oauth.persistence.command.handler;

import com.yapp.core.constant.OAuthProvider;
import com.yapp.core.entity.oauth.entity.OAuthInfo;

import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface OAuthInfoCommandHandler {
    Optional<OAuthInfo> findOne(OAuthProvider provider, String id);
}
