package com.yapp.core.persistance.calander.appointment.persistence.handler;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.yapp.core.persistance.calander.appointment.persistence.repository.AppointmentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppointmentCommandHandlerImpl implements AppointmentCommandHandler {
	private final AppointmentRepository appointmentRepository;

	@Override
	public void save(Consumer<AppointmentRepository> consumer) {
		consumer.accept(appointmentRepository);
	}
}
