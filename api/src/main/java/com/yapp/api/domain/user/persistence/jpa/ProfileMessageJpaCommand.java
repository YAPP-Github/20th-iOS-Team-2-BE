package com.yapp.api.domain.user.persistence.jpa;

import com.yapp.api.domain.user.persistence.ProfileMessageCommand;
import com.yapp.core.entity.user.entity.ProfileMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface ProfileMessageJpaCommand extends JpaRepository<ProfileMessage, Long>, ProfileMessageCommand {
}
