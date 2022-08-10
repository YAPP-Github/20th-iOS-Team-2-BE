package com.yapp.api.domain.family.persitence;

import com.yapp.core.entity.family.persistence.entity.Family;

import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface FamilyQuery {
    Optional<Family> findByCode(String code);
}
