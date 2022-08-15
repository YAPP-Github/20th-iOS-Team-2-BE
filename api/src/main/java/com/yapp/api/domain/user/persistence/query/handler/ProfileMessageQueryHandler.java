package com.yapp.api.domain.user.persistence.query.handler;

import com.yapp.core.entity.user.entity.ProfileMessage;
import com.yapp.core.entity.user.entity.User;

import java.util.List;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface ProfileMessageQueryHandler {
    List<ProfileMessage> findAll(User user);
}
