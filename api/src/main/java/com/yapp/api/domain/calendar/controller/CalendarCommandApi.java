package com.yapp.api.domain.calendar.controller;

import static org.springframework.http.MediaType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.api.domain.calendar.controller.dto.CalendarRequest;
import com.yapp.api.domain.calendar.service.AppointmentService;
import com.yapp.api.global.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.core.persistence.user.entity.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CalendarCommandApi {
	private final AppointmentService appointmentService;

	@PostMapping(value = "/calendar", consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> createCalendar(@AuthenticationHasFamily User user,
										@RequestBody CalendarRequest.Create request) {
		appointmentService.create(user,
								  request.getTitle(),
								  request.getContent(),
								  request.isAllDay(),
								  request.getDate(),
								  request.getTime(),
								  request.isVisibility(),
								  request.getColor());
		return ResponseEntity.ok()
							 .build();
	}

	@PatchMapping(value = "/calendar/{planId}", consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modifyAppointment(@AuthenticationHasFamily User user,
										   @RequestBody CalendarRequest.Modify request,
										   @PathVariable(value = "planId") Long appointmentId) {
		appointmentService.modify(user,
								  appointmentId,
								  request.getColor(),
								  request.getContent(),
								  request.getDate(),
								  request.getTime(),
								  request.getTitle(),
								  request.isAllDay());
		return ResponseEntity.ok()
							 .build();
	}

	@DeleteMapping(value = "/calendar/{eventId}")
	ResponseEntity<Void> remove(@AuthenticationHasFamily User user, @PathVariable("eventId") Long eventId) {
		appointmentService.remove(user, eventId);

		return ResponseEntity.ok()
							 .build();
	}
}
