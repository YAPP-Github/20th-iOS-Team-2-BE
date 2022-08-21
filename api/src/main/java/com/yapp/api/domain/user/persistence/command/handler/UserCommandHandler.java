package com.yapp.api.domain.user.persistence.command.handler;

import com.yapp.realtime.entity.user.entity.User;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface UserCommandHandler {
    void update(User user);

    void save(User user);
}
