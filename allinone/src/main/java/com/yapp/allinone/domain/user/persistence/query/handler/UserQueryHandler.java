package com.yapp.allinone.domain.user.persistence.query.handler;

import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.user.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface UserQueryHandler {
    Optional<User> findOne(Long targetUserId);

    List<User> findAllByFamily(Family family);
}
