package com.yapp.api.domain.calendar.persistence.jpa;

import com.yapp.api.domain.calendar.persistence.VisibilityCommand;
import com.yapp.core.entity.calander.visibility.entity.Visibility;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface VisibilityJpaCommand extends JpaRepository<Visibility, Long>, VisibilityCommand {
}
