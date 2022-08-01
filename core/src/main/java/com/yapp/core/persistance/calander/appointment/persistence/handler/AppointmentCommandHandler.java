package com.yapp.core.persistance.calander.appointment.persistence.handler;

import java.util.function.Consumer;

import com.yapp.core.persistance.calander.appointment.persistence.repository.AppointmentRepository;

public interface AppointmentCommandHandler {
	void save(Consumer<AppointmentRepository> consumer);
}
