package com.yapp.api.domain.family.persitence.query.handler;

import com.yapp.api.domain.family.persitence.FamilyJpaRepository;
import com.yapp.realtime.entity.family.persistence.entity.Family;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class FamilyQueryJpaHandler implements FamilyQueryHandler {
    private final FamilyJpaRepository familyJpaRepository;

    public FamilyQueryJpaHandler(FamilyJpaRepository familyJpaRepository) {
        this.familyJpaRepository = familyJpaRepository;
    }

    @Override
    public Optional<Family> findOne(String code) {
        return familyJpaRepository.findByCode(code);
    }

    @Override
    public Optional<Family> findOne(Long familyId) {
        return familyJpaRepository.findById(familyId);
    }
}
