package com.yapp.core.persistance.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.core.persistance.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {}
