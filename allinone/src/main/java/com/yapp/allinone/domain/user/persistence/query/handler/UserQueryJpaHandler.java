package com.yapp.allinone.domain.user.persistence.query.handler;

import com.yapp.allinone.domain.user.persistence.repository.UserJpaRepository;
import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
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

    @Override
    public List<User> findAllByFamily(Family family) {
        return userJpaRepository.findAllByFamily(family);
    }
}
