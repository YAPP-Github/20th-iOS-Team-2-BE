package com.yapp.realtime.home.socket.repository;

import com.yapp.supporter.entity.user.entity.ProfileMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/28
 * Info :
 **/
public interface ProfileMessageRepository extends JpaRepository<ProfileMessage, Long> {
}
