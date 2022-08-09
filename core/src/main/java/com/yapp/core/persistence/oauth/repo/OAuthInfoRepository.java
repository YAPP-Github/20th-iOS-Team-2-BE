package com.yapp.core.persistence.oauth.repo;

import java.util.Optional;

import com.yapp.core.persistence.oauth.entity.OAuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/02
 * Info : 
 **/
public interface OAuthInfoRepository extends JpaRepository<OAuthInfo, Long> {
	Optional<OAuthInfo> findByProviderAndOauthId(OAuthInfo.OAuthProvider provider, String oauthId);
}
