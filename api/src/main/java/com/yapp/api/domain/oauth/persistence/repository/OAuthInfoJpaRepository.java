package com.yapp.api.domain.oauth.persistence.repository;

import com.yapp.supporter.constant.OAuthProvider;
import com.yapp.supporter.entity.oauth.entity.OAuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/14
 * Info :
 **/
public interface OAuthInfoJpaRepository extends JpaRepository<OAuthInfo, Long> {
    Optional<OAuthInfo> findByOauthIdAndProvider(String oauthId, OAuthProvider provider);
}
