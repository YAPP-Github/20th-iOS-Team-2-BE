package com.yapp.api.domain.user.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.api.domain.user.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {}
