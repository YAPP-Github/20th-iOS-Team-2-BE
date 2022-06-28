package com.yapp.api.domain.user.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yapp.api.domain.user.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query(nativeQuery = true,
		   value = "SELECT u FROM USER u WHERE u.provider = :provider AND u.oauthId = :oauthId")
	Optional<User> findByProviderAndOauthId(String provider, String oauthId);
}
