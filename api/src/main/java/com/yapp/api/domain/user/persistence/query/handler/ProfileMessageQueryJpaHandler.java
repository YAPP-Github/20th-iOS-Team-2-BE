package com.yapp.api.domain.user.persistence.query.handler;

import com.yapp.api.domain.user.persistence.repository.ProfileMessageJpaRepository;
import com.yapp.realtime.entity.user.entity.ProfileMessage;
import com.yapp.realtime.entity.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class ProfileMessageQueryJpaHandler implements ProfileMessageQueryHandler {
    private final ProfileMessageJpaRepository profileMessageJpaRepository;

    public ProfileMessageQueryJpaHandler(ProfileMessageJpaRepository profileMessageJpaRepository) {
        this.profileMessageJpaRepository = profileMessageJpaRepository;
    }

    @Override
    public List<ProfileMessage> findAll(User user) {
        return null;
    }
}
