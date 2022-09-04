package com.yapp.realtime.home.socket.repository;

import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Author : daehwan2yo
 * Date : 2022/08/22
 * Info :
 **/
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByFamily(Family family);
}
