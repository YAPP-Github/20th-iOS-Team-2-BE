package com.yapp.api.domain.oauth.persistence.command.handler;

import com.yapp.api.domain.oauth.persistence.repository.OAuthInfoJpaRepository;
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
public class OAuthInfoCommandJpaHandler implements OAuthInfoCommandHandler {
    private final OAuthInfoJpaRepository oAuthInfoCommand;

    public OAuthInfoCommandJpaHandler(OAuthInfoJpaRepository oAuthInfoCommand) {
        this.oAuthInfoCommand = oAuthInfoCommand;
    }

    @Override
    public Optional<OAuthInfo> findOne(
            OAuthProvider provider, String id) {
        return oAuthInfoCommand.findByIdAndProvider(id, provider);
    }
}