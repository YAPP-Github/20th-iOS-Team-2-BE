package com.yapp.api.domain.family.persistence.handler;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.family.persistence.repository.FamilyRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FamilyQueryHandlerImpl implements FamilyQueryHandler {
	private final FamilyRepository familyRepository;

	@Override
	public Optional<Family> findOne(Function<FamilyRepository, Optional<Family>> function) {
		return function.apply(familyRepository);
	}
}
