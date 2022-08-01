package com.yapp.core.persistance.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.core.persistance.user.entity.ProfileMessage;

public interface ProfileMessageRepository extends JpaRepository<ProfileMessage, Long> {}
