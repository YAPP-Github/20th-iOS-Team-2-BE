package com.yapp.core.persistance.family.persistence.handler;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.yapp.core.persistance.family.persistence.entity.Family;
import com.yapp.core.persistance.family.persistence.repository.FamilyRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FamilyCommandHandlerImpl implements FamilyCommandHandler {
	private final FamilyRepository familyRepository;

	@Override
	public Family saveFamily(Function<FamilyRepository, Family> function) {
		return function.apply(familyRepository);
	}
}
