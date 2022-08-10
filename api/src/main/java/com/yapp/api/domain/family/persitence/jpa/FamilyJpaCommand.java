package com.yapp.api.domain.family.persitence.jpa;

import com.yapp.api.domain.family.persitence.FamilyCommand;
import com.yapp.core.entity.family.persistence.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface FamilyJpaCommand extends JpaRepository<Family, Long>, FamilyCommand {
}
