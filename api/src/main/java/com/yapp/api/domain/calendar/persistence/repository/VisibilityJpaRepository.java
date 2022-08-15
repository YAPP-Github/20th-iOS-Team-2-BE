package com.yapp.api.domain.calendar.persistence.repository;

import com.yapp.core.entity.calander.visibility.entity.Visibility;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/14
 * Info :
 **/
public interface VisibilityJpaRepository extends JpaRepository<Visibility, Long> {
}
