package com.yapp.api.domain.user.jpa;

import com.yapp.core.persistence.user.entity.ProfileMessage;
import com.yapp.core.persistence.user.entity.User;
import com.yapp.core.persistence.user.repository.ProfileMessageCommand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface ProfileMessageJpaCommand extends JpaRepository<ProfileMessage, Long>, ProfileMessageCommand {

    void deleteByOwnerAndId(User user, Long messageId);
}
