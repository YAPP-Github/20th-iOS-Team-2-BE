package com.yapp.core.persistance.user.handler;

import java.util.Optional;
import java.util.function.Function;

import com.yapp.core.persistance.user.entity.User;
import com.yapp.core.persistance.user.repository.UserRepository;

public interface UserQueryHandler {
	Optional<User> findOne(Function<UserRepository, Optional<User>> function);
}
