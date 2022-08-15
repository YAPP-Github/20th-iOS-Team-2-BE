package com.yapp.api.domain.user.persistence.command.handler;

import com.yapp.core.entity.user.entity.ProfileMessage;
import com.yapp.core.entity.user.entity.User;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface ProfileMessageCommandHandler {
    void deleteOne(User user, Long messageId);

    void save(ProfileMessage message);
}