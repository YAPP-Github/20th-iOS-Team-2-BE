package com.yapp.api.domain.family.service;

import static java.util.concurrent.CompletableFuture.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.family.persistence.handler.FamilyCommandHandler;
import com.yapp.api.domain.family.persistence.handler.FamilyQueryHandler;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.api.domain.user.persistence.handler.UserCommandHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FamilyService {
	private final FamilyCommandHandler familyCommandHandler;
	private final FamilyQueryHandler familyQueryHandler;
	private final UserCommandHandler userCommandHandler;
	private final TransactionTemplate transactionTemplate;

	// owner 가 가족을 이미 갖고있는지 검증하는 interceptor 추가해야함
	public Family create(User user, String familyName, String familyMotto) {
		// block
		return transactionTemplate.execute(process -> {
			Family family = familyCommandHandler.saveFamily(repository -> repository.save(new Family(user,
																									 familyName,
																									 familyMotto)));
			family.addUser(user);
			runAsync(() -> userCommandHandler.save(userRepository -> userRepository.save(user)));

			return family;
		});
	}

	@Transactional
	public void modify(User user, String imageLink, String familyName, String familyMotto) {
		user.getFamily()
			.update(imageLink, familyName, familyMotto);
	}
}
