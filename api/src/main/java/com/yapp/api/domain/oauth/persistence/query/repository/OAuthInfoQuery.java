package com.yapp.api.domain.oauth.persistence.query.repository;

import com.yapp.core.entity.oauth.entity.OAuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface OAuthInfoQuery extends JpaRepository<OAuthInfo, Long> {
}
