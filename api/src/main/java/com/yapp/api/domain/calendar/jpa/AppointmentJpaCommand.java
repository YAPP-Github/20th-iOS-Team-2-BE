package com.yapp.api.domain.calendar.jpa;

import com.yapp.core.persistence.calander.appointment.entity.Appointment;
import com.yapp.core.persistence.calander.appointment.repository.AppointmentCommand;
import com.yapp.core.persistence.family.persistence.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface AppointmentJpaCommand extends JpaRepository<Appointment, Long>, AppointmentCommand {
    void deleteByIdAndFamily(Long eventId, Family family);
}
