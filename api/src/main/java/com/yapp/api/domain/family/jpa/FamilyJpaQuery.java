package com.yapp.api.domain.family.jpa;

import com.yapp.core.persistence.family.persistence.entity.Family;
import com.yapp.core.persistence.family.persistence.repository.FamilyQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface FamilyJpaQuery extends JpaRepository<Family, Long>, FamilyQuery {

    Optional<Family> findByCode(String code);
}
