package com.yapp.api.domain.calendar.persistence.query.handler;

import com.yapp.api.domain.calendar.persistence.query.repository.AppointmentQuery;
import com.yapp.core.entity.calander.appointment.entity.Appointment;
import com.yapp.core.entity.family.persistence.entity.Family;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class AppointmentQueryJpaHandler implements AppointmentQueryHandler {
    private final AppointmentQuery appointmentQuery;

    public AppointmentQueryJpaHandler(AppointmentQuery appointmentQuery) {
        this.appointmentQuery = appointmentQuery;
    }

    @Override
    public Optional<Appointment> findOne(
            Long appointmentId, String title) {
        return Optional.empty();
    }

    @Override
    public List<Appointment> findAll(
            Long family, String dateUtilMonth) {
        return null;
    }

    @Override
    public Optional<Appointment> findOne(
            Family family, LocalDate date) {
        return Optional.empty();
    }

    @Override
    public List<Appointment> findAll(
            Family family, LocalDate date) {
        return null;
    }

    @Override
    public List<Appointment> findAll(
            Family family, LocalDate now, LocalDate plusMonths) {
        return null;
    }
}
