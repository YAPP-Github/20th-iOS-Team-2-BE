package com.yapp.api.domain.oauth.jpa;

import com.yapp.core.persistence.oauth.entity.OAuthInfo;
import com.yapp.core.persistence.oauth.repo.OAuthInfoCommand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface OAuthInfoJpaCommand extends JpaRepository<OAuthInfo, Long>, OAuthInfoCommand {
}
