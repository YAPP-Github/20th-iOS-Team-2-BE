package com.yapp.api.domain.family.persitence.query.handler;

import com.yapp.api.domain.family.persitence.query.repository.FamilyQuery;
import com.yapp.core.entity.family.persistence.entity.Family;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class FamilyQueryJpaHandler implements FamilyQueryHandler {
    private final FamilyQuery familyQuery;

    public FamilyQueryJpaHandler(FamilyQuery familyQuery) {
        this.familyQuery = familyQuery;
    }

    @Override
    public Optional<Family> findOne(String code) {
        return Optional.empty();
    }
}
