package com.yapp.api.domain.family.persistence.handler;

import java.util.Optional;
import java.util.function.Function;

import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.family.persistence.repository.FamilyRepository;

public interface FamilyQueryHandler {
	Optional<Family> findOne(Function<FamilyRepository, Optional<Family>> function);
}
