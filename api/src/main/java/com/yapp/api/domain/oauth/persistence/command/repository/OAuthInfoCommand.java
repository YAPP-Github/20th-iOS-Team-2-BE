package com.yapp.api.domain.oauth.persistence.command.repository;

import com.yapp.core.entity.oauth.entity.OAuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface OAuthInfoCommand extends JpaRepository<OAuthInfo, Long> {
}
