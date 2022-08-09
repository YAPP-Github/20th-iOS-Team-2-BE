package com.yapp.core.persistence.user.handler.user;

import java.util.function.Consumer;

import com.yapp.core.persistence.user.repository.UserRepository;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserCommandHandlerImpl implements UserCommandHandler {
	private final UserRepository userRepository;

	@Override
	public void create(Consumer<UserRepository> consumer) {
		consumer.accept(userRepository);
	}
}
