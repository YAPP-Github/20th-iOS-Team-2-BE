package com.yapp.core.persistance.family.persistence.handler;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.yapp.core.persistance.family.persistence.entity.Family;
import com.yapp.core.persistance.family.persistence.repository.FamilyRepository;

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
