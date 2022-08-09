package com.yapp.api.domain.user.jpa;

import com.yapp.core.persistence.user.entity.User;
import com.yapp.core.persistence.user.repository.UserQuery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface UserJpaQuery extends JpaRepository<User, Long>, UserQuery {
}
