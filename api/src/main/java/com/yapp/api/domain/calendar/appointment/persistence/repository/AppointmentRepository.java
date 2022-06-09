package com.yapp.api.domain.calendar.appointment.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.api.domain.calendar.appointment.persistence.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {}
