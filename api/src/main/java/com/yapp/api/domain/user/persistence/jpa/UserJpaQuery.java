package com.yapp.api.domain.user.persistence.jpa;

import com.yapp.api.domain.user.persistence.UserQuery;
import com.yapp.core.entity.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface UserJpaQuery extends JpaRepository<User, Long>, UserQuery {
}
