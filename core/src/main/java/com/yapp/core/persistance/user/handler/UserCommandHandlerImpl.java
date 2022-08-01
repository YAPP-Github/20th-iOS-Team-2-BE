package com.yapp.core.persistance.user.handler;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.yapp.core.persistance.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserCommandHandlerImpl implements UserCommandHandler {
	private final UserRepository userRepository;

	@Override
	public void save(Consumer<UserRepository> consumer) {
		consumer.accept(userRepository);
	}
}
