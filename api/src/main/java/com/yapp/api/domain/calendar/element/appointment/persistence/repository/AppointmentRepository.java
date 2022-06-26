package com.yapp.api.domain.calendar.element.appointment.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.api.domain.calendar.element.appointment.persistence.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	Optional<Appointment> findByIdAndTitle(Long appointmentId, String title);
}
