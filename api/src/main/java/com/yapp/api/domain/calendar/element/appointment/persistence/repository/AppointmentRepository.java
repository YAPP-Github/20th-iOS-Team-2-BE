package com.yapp.api.domain.calendar.element.appointment.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.api.domain.calendar.element.appointment.persistence.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {}
