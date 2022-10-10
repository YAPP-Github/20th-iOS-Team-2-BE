package com.yapp.allinone.domain.user.persistence.query.handler;

import com.yapp.allinone.domain.user.persistence.repository.ProfileMessageJpaRepository;
import com.yapp.supporter.entity.user.entity.ProfileMessage;
import com.yapp.supporter.entity.user.entity.User;
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
