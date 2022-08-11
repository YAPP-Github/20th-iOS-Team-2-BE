package com.yapp.api.domain.family.persitence.command.repository;

import com.yapp.core.entity.family.persistence.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface FamilyCommand extends JpaRepository<Family, Long> {
}
