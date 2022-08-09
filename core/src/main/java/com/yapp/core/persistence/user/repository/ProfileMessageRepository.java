package com.yapp.core.persistence.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.core.persistence.user.entity.ProfileMessage;
import com.yapp.core.persistence.user.entity.User;

public interface ProfileMessageRepository extends JpaRepository<ProfileMessage, Long> {
	List<ProfileMessage> findAllByOwner(User user);

	void deleteByOwnerAndId(User user, Long messageId);
}
