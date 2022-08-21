package com.yapp.api.domain.calendar.service;

import com.yapp.api.domain.calendar.persistence.command.handler.AppointmentCommandHandler;
import com.yapp.api.domain.calendar.persistence.query.handler.AppointmentQueryHandler;
import com.yapp.api.global.error.exception.ApiException;
import com.yapp.supporter.entity.calander.appointment.entity.Appointment;
import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.user.entity.User;
import com.yapp.supporter.error.exception.ExceptionThrowableLayer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.yapp.supporter.error.exception.ErrorCode.APPOINTMENT_NOT_FOUND;
import static com.yapp.supporter.util.DateUtils.yearMonth;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AppointmentService implements ExceptionThrowableLayer {
    private final AppointmentCommandHandler appointmentCommandHandler;
    private final AppointmentQueryHandler appointmentQueryHandler;

    @Transactional
    public void create(
            User user,
            String title,
            String content,
            boolean allDay,
            LocalDate date,
            LocalTime time,
            boolean visibility,
            String color) {

        // allDay 관련 작업
        // visibility 관련 작업
        appointmentCommandHandler.save(Appointment.of(title, content, date, time, allDay, color, user.getFamily(), user));
    }

    @Transactional
    public void modify(
            User user,
            Long appointmentId,
            String color,
            String content,
            LocalDate date,
            LocalTime time,
            String title,
            boolean allDay) {
        Appointment appointment = appointmentQueryHandler.findOne(appointmentId)
                .orElseThrow(() -> new ApiException(APPOINTMENT_NOT_FOUND, packageName(this.getClass())));

        appointment.patch(title, color, content, date, time);
    }

    public List<Appointment> retrieveAsMonth(Family family, String year, String month) {
        return appointmentQueryHandler.findAll(family.getId(), yearMonth(year, month));
    }

    public List<Appointment> retrieveAsDay(Family family, LocalDate date) {
        return appointmentQueryHandler.findAll(family, date);
    }

    @Transactional
    public void remove(Family family, Long appointmentId) {
        appointmentCommandHandler.remove(appointmentId, family);
    }
}
