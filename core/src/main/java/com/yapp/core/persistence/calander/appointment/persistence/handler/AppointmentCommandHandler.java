package com.yapp.core.persistence.calander.appointment.persistence.handler;

import java.util.function.Consumer;

import com.yapp.core.persistence.calander.appointment.persistence.repository.AppointmentRepository;

public interface AppointmentCommandHandler {
	void create(Consumer<AppointmentRepository> consumer);

	void remove(Consumer<AppointmentRepository> consumer);
}
