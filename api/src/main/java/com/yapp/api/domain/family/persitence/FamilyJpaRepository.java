package com.yapp.api.domain.family.persitence;

import com.yapp.supporter.entity.family.persistence.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/14
 * Info :
 **/
public interface FamilyJpaRepository extends JpaRepository<Family, Long> {
    Optional<Family> findByCode(String code);
}
