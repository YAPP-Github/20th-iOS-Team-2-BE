package com.yapp.core.persistence.family.persistence.handler;

import java.util.function.Function;

import com.yapp.core.persistence.family.persistence.repository.FamilyRepository;
import org.springframework.stereotype.Component;

import com.yapp.core.persistence.family.persistence.entity.Family;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FamilyCommandHandlerImpl implements FamilyCommandHandler {
	private final FamilyRepository familyRepository;

	@Override
	public Family create(Function<FamilyRepository, Family> function) {
		return function.apply(familyRepository);
	}
}
