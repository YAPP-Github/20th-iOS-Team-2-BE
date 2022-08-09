package com.yapp.core.persistence.calander.appointment.persistence.handler;

import java.util.function.Consumer;

import com.yapp.core.persistence.calander.appointment.persistence.repository.AppointmentRepository;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppointmentCommandHandlerImpl implements AppointmentCommandHandler {
	private final AppointmentRepository appointmentRepository;

	@Override
	public void create(Consumer<AppointmentRepository> consumer) {
		consumer.accept(appointmentRepository);
	}

	@Override
	public void remove(Consumer<AppointmentRepository> consumer) {
		consumer.accept(appointmentRepository);
	}
}
