package com.yapp.api.domain.calendar.persistence.command.repository;

import com.yapp.core.entity.calander.visibility.entity.Visibility;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface VisibilityCommand extends JpaRepository<Visibility, Long> {
}
