package com.yapp.core.persistance.oauth.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.core.persistance.oauth.entity.OAuthInfo;

/**
 * Author : daehwan2yo
 * Date : 2022/08/02
 * Info : 
 **/
public interface OAuthInfoRepository extends JpaRepository<OAuthInfo, Long> {
	Optional<OAuthInfo> findByProviderAndOauthId(OAuthInfo.OAuthProvider provider, String oauthId);
}
