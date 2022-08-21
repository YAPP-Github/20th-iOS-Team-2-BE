package com.yapp.api.domain.user.persistence.query.handler;

import com.yapp.api.domain.user.persistence.repository.UserJpaRepository;
import com.yapp.supporter.entity.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class UserQueryJpaHandler implements UserQueryHandler {
    private final UserJpaRepository userJpaRepository;

    public UserQueryJpaHandler(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<User> findOne(Long targetUserId) {
        return userJpaRepository.findById(targetUserId);
    }
}
