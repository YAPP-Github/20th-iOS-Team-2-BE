package com.yapp.api.domain.user.persistence.repository;

import com.yapp.core.entity.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/14
 * Info :
 **/
public interface UserJpaRepository extends JpaRepository<User, Long> {
}
