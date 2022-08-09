package com.yapp.core.persistence.family.persistence.handler;

import java.util.Optional;
import java.util.function.Function;

import com.yapp.core.persistence.family.persistence.repository.FamilyRepository;
import com.yapp.core.persistence.family.persistence.entity.Family;

public interface FamilyQueryHandler {
	Optional<Family> findOne(Function<FamilyRepository, Optional<Family>> function);
}
