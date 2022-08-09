package com.yapp.core.persistence.user.handler.user;

import java.util.Optional;
import java.util.function.Function;

import com.yapp.core.persistence.user.entity.User;
import com.yapp.core.persistence.user.repository.UserRepository;
import org.springframework.stereotype.Component;

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
