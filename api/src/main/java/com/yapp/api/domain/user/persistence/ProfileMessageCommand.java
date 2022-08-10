package com.yapp.api.domain.user.persistence;

import com.yapp.core.entity.user.entity.User;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface ProfileMessageCommand {
    void deleteByOwnerAndId(User user, Long messageId);
}
