package com.yapp.core.persistance.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yapp.core.persistance.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
