package com.yapp.api.domain.calendar.element.appointment.persistence.handler;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.yapp.api.domain.calendar.element.appointment.persistence.entity.Appointment;
import com.yapp.api.domain.calendar.element.appointment.persistence.repository.AppointmentRepository;

public interface AppointmentQueryHandler {
	Optional<Appointment> findOne(Function<AppointmentRepository, Optional<Appointment>> function);

	List<Appointment> findAll(Function<AppointmentRepository, List<Appointment>> function);
}
