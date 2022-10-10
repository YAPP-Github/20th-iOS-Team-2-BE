package com.yapp.allinone.domain.calendar.persistence.repository;

import com.yapp.supporter.entity.calander.visibility.entity.Visibility;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/14
 * Info :
 **/
public interface VisibilityJpaRepository extends JpaRepository<Visibility, Long> {
}
