package com.yapp.api.domain.user.persistence;

import com.yapp.core.entity.user.entity.ProfileMessage;
import com.yapp.core.entity.user.entity.User;

import java.util.List;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface ProfileMessageQuery {
    List<ProfileMessage> findAllByOwner(User user);
}
