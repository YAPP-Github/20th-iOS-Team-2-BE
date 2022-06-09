package com.yapp.api.domain.calendar.element.appointment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.api.domain.calendar.element.appointment.persistence.handler.AppointmentCommandHandler;
import com.yapp.api.domain.calendar.element.appointment.persistence.handler.AppointmentQueryHandler;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AppointmentService {
	private final AppointmentCommandHandler appointmentCommandHandler;
	private final AppointmentQueryHandler appointmentQueryHandler;
}
