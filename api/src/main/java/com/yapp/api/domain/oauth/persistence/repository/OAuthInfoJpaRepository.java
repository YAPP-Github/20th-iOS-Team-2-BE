package com.yapp.api.domain.oauth.persistence.repository;

import com.yapp.realtime.constant.OAuthProvider;
import com.yapp.realtime.entity.oauth.entity.OAuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/14
 * Info :
 **/
public interface OAuthInfoJpaRepository extends JpaRepository<OAuthInfo, Long> {
    Optional<OAuthInfo> findByOauthIdAndProvider(String oauthId, OAuthProvider provider);

    Optional<OAuthInfo> findByIdAndProvider(String id, OAuthProvider provider);
}
