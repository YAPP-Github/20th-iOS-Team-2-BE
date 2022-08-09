package com.yapp.core.persistence.family.persistence.handler;

import java.util.function.Function;

import com.yapp.core.persistence.family.persistence.repository.FamilyRepository;
import com.yapp.core.persistence.family.persistence.entity.Family;

public interface FamilyCommandHandler {
	Family create(Function<FamilyRepository, Family> function);
}
