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

	public Family create(User owner, String familyName, String familyMotto) {
		return null;
	}

	public void modify(User user, String imageLink, String familyName, String familyMotto) {

	}
}
