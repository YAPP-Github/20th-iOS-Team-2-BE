package com.yapp.core.persistance.family.persistence.handler;

import java.util.Optional;
import java.util.function.Function;

import com.yapp.core.persistance.family.persistence.entity.Family;
import com.yapp.core.persistance.family.persistence.repository.FamilyRepository;

public interface FamilyQueryHandler {
	Optional<Family> findOne(Function<FamilyRepository, Optional<Family>> function);
}
