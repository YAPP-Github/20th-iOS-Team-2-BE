package com.yapp.api.domain.calendar.element.appointment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.api.domain.calendar.element.appointment.persistence.entity.Appointment;
import com.yapp.api.domain.calendar.element.appointment.persistence.handler.AppointmentCommandHandler;
import com.yapp.api.domain.calendar.element.appointment.persistence.handler.AppointmentQueryHandler;
import com.yapp.api.domain.user.persistence.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AppointmentService {
	private final AppointmentCommandHandler appointmentCommandHandler;
	private final AppointmentQueryHandler appointmentQueryHandler;

	@Transactional
	public void create(User user,
					   String title,
					   String content,
					   boolean allDay,
					   String date,
					   String time,
					   boolean visibility,
					   String color) {

		// allDay 관련 작업
		// visibility 관련 작업
		appointmentCommandHandler.save(repository -> repository.save(Appointment.of(title,
																					content,
																					date,
																					time,
																					allDay,
																					color,
																					user.getFamily(),
																					user)));
	}

	@Transactional
	public void modify(User user,
					   Long appointmentId,
					   String color,
					   String content,
					   String date,
					   String time,
					   String title,
					   boolean allDay) {
		appointmentQueryHandler.findOne(repository -> repository.findByIdAndTitle(appointmentId, title));
	}
}
