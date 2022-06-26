package com.yapp.api.domain.calendar.element.appointment.persistence.handler;

import java.util.function.Consumer;

import com.yapp.api.domain.calendar.element.appointment.persistence.repository.AppointmentRepository;

public interface AppointmentCommandHandler {
	void save(Consumer<AppointmentRepository> consumer);
}
