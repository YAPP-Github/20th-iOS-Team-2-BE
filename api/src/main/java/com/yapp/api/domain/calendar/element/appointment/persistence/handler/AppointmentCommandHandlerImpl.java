package com.yapp.api.domain.calendar.element.appointment.persistence.handler;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.yapp.api.domain.calendar.element.appointment.persistence.repository.AppointmentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppointmentCommandHandlerImpl implements AppointmentCommandHandler {
	private AppointmentRepository appointmentRepository;

	@Override
	public void save(Consumer<AppointmentRepository> consumer) {
		consumer.accept(appointmentRepository);
	}
}
