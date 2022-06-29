package com.yapp.api.domain.user.persistence.handler;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.yapp.api.domain.user.persistence.repository.UserRepository;

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
