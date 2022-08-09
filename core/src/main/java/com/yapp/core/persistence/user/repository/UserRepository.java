package com.yapp.core.persistence.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.core.persistence.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
