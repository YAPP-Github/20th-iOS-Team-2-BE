package com.yapp.api.domain.calendar.controller;

import static org.springframework.http.MediaType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.api.domain.calendar.controller.dto.CalendarResponse;
import com.yapp.api.domain.calendar.service.AppointmentService;
import com.yapp.core.persistance.user.entity.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CalendarQueryApi {
	private final AppointmentService appointmentService;

	@GetMapping(value = "/calendar/as-month", produces = APPLICATION_JSON_VALUE)
	ResponseEntity<CalendarResponse.AsMonth> retrieveAsMonth(User user,
															 @RequestParam(name = "year") String year,
															 @RequestParam(name = "month") String month) {
		appointmentService.retrieveAsMonth(user, year, month);
		return null;
	}

	@GetMapping(value = "/calendar/as-day", produces = APPLICATION_JSON_VALUE)
	ResponseEntity<CalendarResponse.AsDay> retrieveAsDay(User user, @RequestParam(name = "date") String date) {
		appointmentService.retrieveAsDay(user, date);
		return null;
	}
}
