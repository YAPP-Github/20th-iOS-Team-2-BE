package com.yapp.api.domain.oauth.persistence.command.handler;

import com.yapp.api.domain.oauth.persistence.command.repository.OAuthInfoCommand;
import org.springframework.stereotype.Component;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class OAuthInfoCommandJpaHandler implements OAuthInfoCommandHandler {
    private final OAuthInfoCommand oAuthInfoCommand;

    public OAuthInfoCommandJpaHandler(OAuthInfoCommand oAuthInfoCommand) {
        this.oAuthInfoCommand = oAuthInfoCommand;
    }
}
