package com.yapp.core.persistance.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.core.persistance.user.entity.ProfileMessage;
import com.yapp.core.persistance.user.entity.User;

public interface ProfileMessageRepository extends JpaRepository<ProfileMessage, Long> {
	List<ProfileMessage> findAllByOwner(User user);

	void deleteByOwnerAndId(User user, Long messageId);
}
