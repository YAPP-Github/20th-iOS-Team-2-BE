package com.yapp.api.domain.calendar.persistence.query.handler;

import com.yapp.api.domain.calendar.persistence.repository.AppointmentJpaRepository;
import com.yapp.supporter.entity.calander.appointment.entity.Appointment;
import com.yapp.supporter.entity.family.persistence.entity.Family;
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
    private final AppointmentJpaRepository appointmentJpaRepository;

    public AppointmentQueryJpaHandler(AppointmentJpaRepository appointmentJpaRepository) {
        this.appointmentJpaRepository = appointmentJpaRepository;
    }

    @Override
    public Optional<Appointment> findOne(
            Long appointmentId) {
        return appointmentJpaRepository.findById(appointmentId);
    }

    @Override
    public List<Appointment> findAll(
            Long familyId, String yearMonth) {
        return appointmentJpaRepository.findAllByFamilyAndYearMonth(familyId, yearMonth);
    }

    @Override
    public Optional<Appointment> findOne(
            Family family, LocalDate date) {
        return appointmentJpaRepository.findByFamilyAndDate(family, date);
    }

    @Override
    public List<Appointment> findAll(
            Family family, LocalDate date) {
        return appointmentJpaRepository.findAllByFamilyAndDate(family, date);
    }

    @Override
    public List<Appointment> findAll(
            Family family, LocalDate now, LocalDate plusMonths) {
        return appointmentJpaRepository.findAllByFamilyAndDateBetween(family, now, plusMonths);
    }
}
