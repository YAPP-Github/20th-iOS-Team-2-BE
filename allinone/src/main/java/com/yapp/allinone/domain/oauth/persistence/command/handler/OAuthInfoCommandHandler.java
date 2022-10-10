package com.yapp.allinone.domain.oauth.persistence.command.handler;

import com.yapp.supporter.constant.OAuthProvider;
import com.yapp.supporter.entity.oauth.entity.OAuthInfo;

import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface OAuthInfoCommandHandler {
    Optional<OAuthInfo> findOne(OAuthProvider provider, String id);
}
