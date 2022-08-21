package com.yapp.api.domain.user.persistence.repository;

import com.yapp.supporter.entity.user.entity.ProfileMessage;
import com.yapp.supporter.entity.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/14
 * Info :
 **/
public interface ProfileMessageJpaRepository extends JpaRepository<ProfileMessage, Long> {
    void deleteByIdAndOwner(Long messageId, User owner);
}
