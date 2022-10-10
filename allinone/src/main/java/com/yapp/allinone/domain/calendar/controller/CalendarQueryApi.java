package com.yapp.allinone.domain.calendar.controller;

import com.yapp.allinone.common.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.allinone.domain.calendar.controller.model.CalendarResponse;
import com.yapp.allinone.domain.calendar.service.AppointmentService;
import com.yapp.supporter.entity.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class CalendarQueryApi {
    private final AppointmentService appointmentService;

    @GetMapping(value = "/calendar/as-month", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<CalendarResponse.AsMonth> retrieveAsMonth(
            @AuthenticationHasFamily User user,
            @RequestParam(name = "year") String year,
            @RequestParam(name = "month") String month) {
        return ResponseEntity.ok(CalendarResponse.AsMonth.of(appointmentService.retrieveAsMonth(user.getFamily(), year, month)));
    }

    @GetMapping(value = "/calendar/as-day", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<CalendarResponse.AsDay> retrieveAsDay(
            @AuthenticationHasFamily User user, @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok(CalendarResponse.AsDay.from(appointmentService.retrieveAsDay(user.getFamily(), date)));
    }
}
