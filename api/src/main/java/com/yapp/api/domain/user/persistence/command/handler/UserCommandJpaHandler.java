package com.yapp.api.domain.user.persistence.command.handler;

import com.yapp.api.domain.user.persistence.repository.UserJpaRepository;
import com.yapp.core.entity.user.entity.User;
import org.springframework.stereotype.Component;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class UserCommandJpaHandler implements UserCommandHandler {
    private final UserJpaRepository userJpaRepository;

    public UserCommandJpaHandler(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public void update(User user) {
        userJpaRepository.save(user);
    }

    @Override
    public void save(User user) {
        userJpaRepository.save(user);
    }
}
