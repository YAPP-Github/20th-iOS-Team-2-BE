package com.yapp.api.domain.user.persistence.handler;

import java.util.Optional;
import java.util.function.Function;

import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.api.domain.user.persistence.repository.UserRepository;

public interface UserQueryHandler {
	Optional<User> findOne(Function<UserRepository, Optional<User>> function);
}
