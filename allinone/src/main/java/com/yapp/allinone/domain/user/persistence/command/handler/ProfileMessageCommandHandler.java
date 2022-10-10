package com.yapp.allinone.domain.user.persistence.command.handler;

import com.yapp.supporter.entity.user.entity.ProfileMessage;
import com.yapp.supporter.entity.user.entity.User;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface ProfileMessageCommandHandler {
    void deleteOne(User user, Long messageId);

    void save(ProfileMessage message);
}