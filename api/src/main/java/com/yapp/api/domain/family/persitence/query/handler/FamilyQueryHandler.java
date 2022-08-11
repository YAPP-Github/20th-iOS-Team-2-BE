package com.yapp.api.domain.family.persitence.query.handler;

import com.yapp.core.entity.family.persistence.entity.Family;

import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface FamilyQueryHandler {
    Optional<Family> findOne(String code);
}
