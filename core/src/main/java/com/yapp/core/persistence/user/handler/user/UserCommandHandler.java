package com.yapp.core.persistence.user.handler.user;

import java.util.function.Consumer;

import com.yapp.core.persistence.user.repository.UserRepository;

public interface UserCommandHandler {
	void create(Consumer<UserRepository> consumer);
}
