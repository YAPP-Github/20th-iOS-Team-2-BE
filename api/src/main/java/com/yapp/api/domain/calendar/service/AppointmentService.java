package com.yapp.api.domain.calendar.service;

import static com.yapp.core.error.exception.ErrorCode.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yapp.core.error.exception.BaseBusinessException;
import com.yapp.core.persistance.calander.appointment.persistence.entity.Appointment;
import com.yapp.core.persistance.calander.appointment.persistence.handler.AppointmentCommandHandler;
import com.yapp.core.persistance.calander.appointment.persistence.handler.AppointmentQueryHandler;
import com.yapp.core.persistance.family.persistence.repository.FamilyRepository;
import com.yapp.core.persistance.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AppointmentService {
	private final AppointmentCommandHandler appointmentCommandHandler;
	private final AppointmentQueryHandler appointmentQueryHandler;
	private final FamilyRepository familyRepository;

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
		appointmentQueryHandler.findOne(repository -> repository.findByIdAndTitle(appointmentId, title))
							   .orElseThrow(() -> new BaseBusinessException(APPOINTMENT_NOT_FOUND,
																			new RuntimeException(
																				"Appointment not found error : which occurred PATCH /calendar/{id}")));
	}

	public List<Appointment> retrieveAsMonth(User user, String year, String month) {
		return appointmentQueryHandler.findAll(repository -> repository.findByFamilyAndDateUntilMonth(user.getFamily().getId(),
																									  dateUtilMonth(year,
																													month)));
	}

	private String dateUtilMonth(String year, String month) {
		if (month.length() == 1) {
			month = "0" + month;
		}
		return year + "-" + month;
	}

	public List<Appointment> retrieveAsDay(User user, String date) {
		return appointmentQueryHandler.findAll(repository -> repository.findAllByFamilyAndDate(user.getFamily(),
																							   LocalDate.parse(date,
																											   DateTimeFormatter.ISO_DATE)));
	}
}
