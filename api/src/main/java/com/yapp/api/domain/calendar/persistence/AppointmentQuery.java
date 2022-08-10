package com.yapp.api.domain.calendar.persistence;

import com.yapp.core.entity.calander.appointment.entity.Appointment;
import com.yapp.core.entity.family.persistence.entity.Family;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface AppointmentQuery {
    Optional<Appointment> findByIdAndTitle(Long appointmentId, String title);

    List<Appointment> findByFamilyAndDateUntilMonth(Long family, String dateUtilMonth);

    Optional<Appointment> findByFamilyAndDate(Family family, LocalDate date);

    List<Appointment> findAllByFamilyAndDate(Family family, LocalDate date);

    List<Appointment> findAllByFamilyAndDateBetween(Family family, LocalDate now, LocalDate plusMonths);
}
