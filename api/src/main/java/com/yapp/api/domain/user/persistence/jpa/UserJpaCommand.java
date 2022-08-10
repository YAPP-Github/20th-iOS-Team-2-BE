package com.yapp.api.domain.user.persistence.jpa;

import com.yapp.api.domain.user.persistence.UserCommand;
import com.yapp.core.entity.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface UserJpaCommand extends JpaRepository<User, Long>, UserCommand {
}
