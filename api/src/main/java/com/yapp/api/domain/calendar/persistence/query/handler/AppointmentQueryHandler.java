package com.yapp.api.domain.calendar.persistence.query.handler;

import com.yapp.core.entity.calander.appointment.entity.Appointment;
import com.yapp.core.entity.family.persistence.entity.Family;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface AppointmentQueryHandler {
    Optional<Appointment> findOne(Long appointmentId, String title);

    Optional<Appointment> findOne(Family family, LocalDate date);

    List<Appointment> findAll(Long family, String dateUtilMonth);

    List<Appointment> findAll(Family family, LocalDate date);

    List<Appointment> findAll(Family family, LocalDate now, LocalDate plusMonths);
}
