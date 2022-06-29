package com.yapp.api.domain.user.persistence.handler;

import java.util.function.Consumer;

import com.yapp.api.domain.user.persistence.repository.UserRepository;

public interface UserCommandHandler {
	void save(Consumer<UserRepository> consumer);
}
