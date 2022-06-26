package com.yapp.api.domain.calendar.element.appointment.persistence.handler;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.yapp.api.domain.calendar.element.appointment.persistence.entity.Appointment;
import com.yapp.api.domain.calendar.element.appointment.persistence.repository.AppointmentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppointQueryHandlerImpl implements AppointmentQueryHandler {
	private final AppointmentRepository appointmentRepository;

	@Override
	public Optional<Appointment> findOne(Function<AppointmentRepository, Optional<Appointment>> function) {
		return function.apply(appointmentRepository);
	}
}
