package com.yapp.api.domain.calendar.jpa;

import com.yapp.core.persistence.calander.visibility.entity.Visibility;
import com.yapp.core.persistence.calander.visibility.repository.VisibilityCommand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface VisibilityJpaCommand extends JpaRepository<Visibility, Long>, VisibilityCommand {
}
