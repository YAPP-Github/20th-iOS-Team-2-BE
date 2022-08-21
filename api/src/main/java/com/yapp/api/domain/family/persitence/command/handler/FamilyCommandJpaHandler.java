package com.yapp.api.domain.family.persitence.command.handler;

import com.yapp.api.domain.family.persitence.FamilyJpaRepository;
import com.yapp.realtime.entity.family.persistence.entity.Family;
import org.springframework.stereotype.Component;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class FamilyCommandJpaHandler implements FamilyCommandHandler {
    private final FamilyJpaRepository familyJpaRepository;

    public FamilyCommandJpaHandler(FamilyJpaRepository familyJpaRepository) {
        this.familyJpaRepository = familyJpaRepository;
    }

    @Override
    public Family save(Family family) {
        return familyJpaRepository.save(family);
    }
}
