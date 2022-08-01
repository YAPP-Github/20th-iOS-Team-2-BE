package com.yapp.core.persistance.user.handler;

import java.util.function.Consumer;

import com.yapp.core.persistance.user.repository.UserRepository;

public interface UserCommandHandler {
	void save(Consumer<UserRepository> consumer);
}
