package com.yapp.core.persistence.user.handler.user;

import java.util.Optional;
import java.util.function.Function;

import com.yapp.core.persistence.user.repository.UserRepository;
import com.yapp.core.persistence.user.entity.User;

public interface UserQueryHandler {
	Optional<User> findOne(Function<UserRepository, Optional<User>> function);
}
