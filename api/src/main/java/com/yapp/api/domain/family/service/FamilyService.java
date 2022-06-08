package com.yapp.api.domain.family.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.api.domain.family.persistence.handler.FamilyCommandHandler;
import com.yapp.api.domain.family.persistence.handler.FamilyQueryHandler;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FamilyService {
	private final FamilyCommandHandler familyCommandHandler;
	private final FamilyQueryHandler familyQueryHandler;

}
