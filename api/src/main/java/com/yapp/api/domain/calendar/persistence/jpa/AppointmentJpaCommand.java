package com.yapp.api.domain.calendar.persistence.jpa;

import com.yapp.api.domain.calendar.persistence.AppointmentCommand;
import com.yapp.core.entity.calander.appointment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface AppointmentJpaCommand extends JpaRepository<Appointment, Long>, AppointmentCommand {
}
