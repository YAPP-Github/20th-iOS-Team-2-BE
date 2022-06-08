package com.yapp.api.domain.user.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.api.domain.user.persistence.entity.ProfileMessage;

public interface ProfileMessageRepository extends JpaRepository<ProfileMessage, Long> {}
