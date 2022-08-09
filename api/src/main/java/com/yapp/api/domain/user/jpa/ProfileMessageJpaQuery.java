package com.yapp.api.domain.user.jpa;

import com.yapp.core.persistence.user.entity.ProfileMessage;
import com.yapp.core.persistence.user.entity.User;
import com.yapp.core.persistence.user.repository.ProfileMessageQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface ProfileMessageJpaQuery extends JpaRepository<ProfileMessage, Long>, ProfileMessageQuery {
    List<ProfileMessage> findAllByOwner(User user);
}
