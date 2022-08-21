package com.yapp.api.domain.user.persistence.command.handler;

import com.yapp.api.domain.user.persistence.repository.ProfileMessageJpaRepository;
import com.yapp.supporter.entity.user.entity.ProfileMessage;
import com.yapp.supporter.entity.user.entity.User;
import org.springframework.stereotype.Component;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class ProfileMessageCommandJpaHandler implements ProfileMessageCommandHandler {
    private final ProfileMessageJpaRepository profileMessageJpaRepository;

    public ProfileMessageCommandJpaHandler(ProfileMessageJpaRepository profileMessageJpaRepository) {
        this.profileMessageJpaRepository = profileMessageJpaRepository;
    }

    @Override
    public void deleteOne(User owner, Long messageId) {
        profileMessageJpaRepository.deleteByIdAndOwner(messageId, owner);
    }

    @Override
    public void save(ProfileMessage message) {
        profileMessageJpaRepository.save(message);
    }
}
