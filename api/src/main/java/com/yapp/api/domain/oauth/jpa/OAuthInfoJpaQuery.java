package com.yapp.api.domain.oauth.jpa;

import com.yapp.core.constant.OAuthProvider;
import com.yapp.core.persistence.oauth.entity.OAuthInfo;
import com.yapp.core.persistence.oauth.repo.OAuthInfoQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface OAuthInfoJpaQuery extends JpaRepository<OAuthInfo, Long>, OAuthInfoQuery {

    Optional<OAuthInfo> findByProviderAndOauthId(OAuthProvider provider, String oauthId);
}
