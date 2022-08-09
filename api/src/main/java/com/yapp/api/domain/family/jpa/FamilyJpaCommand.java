package com.yapp.api.domain.family.jpa;

import com.yapp.core.persistence.family.persistence.entity.Family;
import com.yapp.core.persistence.family.persistence.repository.FamilyCommand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface FamilyJpaCommand extends JpaRepository<Family, Long>, FamilyCommand {
}
