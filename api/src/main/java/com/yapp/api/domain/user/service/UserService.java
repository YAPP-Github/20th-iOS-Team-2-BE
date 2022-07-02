package com.yapp.api.domain.user.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.api.domain.user.persistence.handler.ProfileMessageCommandHandler;
import com.yapp.api.domain.user.persistence.handler.ProfileMessageQueryHandler;
import com.yapp.api.domain.user.persistence.handler.UserCommandHandler;
import com.yapp.api.domain.user.persistence.handler.UserQueryHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserCommandHandler userCommandHandler;
	private final UserQueryHandler userQueryHandler;
	private final ProfileMessageCommandHandler profileMessageCommandHandler;
	private final ProfileMessageQueryHandler profileMessageQueryHandler;
	private final TransactionTemplate transactionTemplate;

	public void create(User user, String name, String nickname, String roleInFamily, LocalDate birthday) {
		transactionTemplate.executeWithoutResult(process -> {
			user.update(name, nickname, roleInFamily, birthday);
			userCommandHandler.save(userRepository -> userRepository.save(user));
		});
	}

	public void modify(User user, String nickname, String imageLink, LocalDate birthDay, String roleInFamily) {
		transactionTemplate.executeWithoutResult(process -> {
			user.update(nickname, imageLink, birthDay, roleInFamily);
			userCommandHandler.save(userRepository -> userRepository.save(user));
		});
	}
}
