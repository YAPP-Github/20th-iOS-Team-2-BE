package com.yapp.core.persistance.user.handler;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.yapp.core.persistance.user.entity.User;
import com.yapp.core.persistance.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserQueryHandlerImpl implements UserQueryHandler {
	private final UserRepository userRepository;

	@Override
	public Optional<User> findOne(Function<UserRepository, Optional<User>> function) {
		return function.apply(userRepository);
	}
}
