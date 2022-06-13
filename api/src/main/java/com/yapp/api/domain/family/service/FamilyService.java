package com.yapp.api.domain.family.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.family.persistence.handler.FamilyCommandHandler;
import com.yapp.api.domain.family.persistence.handler.FamilyQueryHandler;
import com.yapp.api.domain.user.persistence.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FamilyService {
	private final FamilyCommandHandler familyCommandHandler;
	private final FamilyQueryHandler familyQueryHandler;

	// owner 가 가족을 이미 갖고있는지 검증하는 interceptor 추가해야함
	public Family create(User user, String familyName, String familyMotto) {
		return familyCommandHandler.saveFamily(repository -> repository.save(new Family(user,
																						familyName,
																						familyMotto)));
	}

	public void modify(User user, String imageLink, String familyName, String familyMotto) {
		user.getFamily()
			.update(imageLink, familyName, familyMotto);
	}
}
