package com.yapp.api.domain.user.persistence.jpa;

import com.yapp.api.domain.user.persistence.ProfileMessageQuery;
import com.yapp.core.entity.user.entity.ProfileMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface ProfileMessageJpaQuery extends JpaRepository<ProfileMessage, Long>, ProfileMessageQuery {

}
