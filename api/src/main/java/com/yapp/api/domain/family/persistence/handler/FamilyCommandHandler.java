package com.yapp.api.domain.family.persistence.handler;

import java.util.function.Function;

import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.family.persistence.repository.FamilyRepository;

public interface FamilyCommandHandler {
	Family saveFamily(Function<FamilyRepository, Family> function);
}
