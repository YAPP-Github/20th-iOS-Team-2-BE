package com.yapp.api.domain.oauth.persistence.jpa;

import com.yapp.core.entity.oauth.entity.OAuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface OAuthInfoJpaCommand extends JpaRepository<OAuthInfo, Long> {
}
