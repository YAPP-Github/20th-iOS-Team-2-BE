package com.yapp.api.domain.family.persitence.query.repository;

import com.yapp.core.entity.family.persistence.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface FamilyQuery extends JpaRepository<Family, Long> {
}
