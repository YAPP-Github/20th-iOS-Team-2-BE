package com.yapp.api.domain.user.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yapp.api.domain.user.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query(nativeQuery = true,
		   value = "SELECT * FROM users u LEFT JOIN oauth_info ON u.id = o.user_id WHERE o.provider = :provider AND o.oauth_Id = :oauthId")
	Optional<User> findByProviderAndOauthId(String provider, String oauthId);
}
