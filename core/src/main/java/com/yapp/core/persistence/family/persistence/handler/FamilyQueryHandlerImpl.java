package com.yapp.core.persistence.family.persistence.handler;

import java.util.Optional;
import java.util.function.Function;

import com.yapp.core.persistence.family.persistence.repository.FamilyRepository;
import org.springframework.stereotype.Component;

import com.yapp.core.persistence.family.persistence.entity.Family;

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
