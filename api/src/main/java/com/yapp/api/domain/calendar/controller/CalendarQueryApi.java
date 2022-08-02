package com.yapp.api.domain.calendar.controller;

import static org.springframework.http.MediaType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.api.domain.calendar.controller.dto.CalendarResponse;
import com.yapp.api.domain.calendar.service.AppointmentService;
import com.yapp.api.global.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.core.persistance.user.entity.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CalendarQueryApi {
	private final AppointmentService appointmentService;

	@GetMapping(value = "/calendar/as-month", produces = APPLICATION_JSON_VALUE)
	ResponseEntity<CalendarResponse.AsMonth> retrieveAsMonth(@AuthenticationHasFamily User user,
															 @RequestParam(name = "year") String year,
															 @RequestParam(name = "month") String month) {
		return ResponseEntity.ok(CalendarResponse.AsMonth.of(appointmentService.retrieveAsMonth(user, year, month)));
	}

	@GetMapping(value = "/calendar/as-day", produces = APPLICATION_JSON_VALUE)
	ResponseEntity<CalendarResponse.AsDay> retrieveAsDay(@AuthenticationHasFamily User user, @RequestParam(name = "date") String date) {
		return ResponseEntity.ok(CalendarResponse.AsDay.from(appointmentService.retrieveAsDay(user, date)));
	}
}
