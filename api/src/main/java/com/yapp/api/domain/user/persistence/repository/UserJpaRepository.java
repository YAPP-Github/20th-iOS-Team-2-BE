package com.yapp.api.domain.user.persistence.repository;

import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author : daehwan2yo
 * Date : 2022/08/14
 * Info :
 **/
public interface UserJpaRepository extends JpaRepository<User, Long> {
    List<User> findAllByFamily(Family family);
}
