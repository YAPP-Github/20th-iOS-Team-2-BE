package com.yapp.api.domain.calendar.persistence.command.handler;

import com.yapp.api.domain.calendar.persistence.command.repository.AppointmentCommand;
import com.yapp.core.entity.calander.appointment.entity.Appointment;
import com.yapp.core.entity.family.persistence.entity.Family;
import org.springframework.stereotype.Component;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class AppointmentCommandJpaHandler implements AppointmentCommandHandler {
    private final AppointmentCommand appointmentCommand;

    public AppointmentCommandJpaHandler(AppointmentCommand appointmentCommand) {
        this.appointmentCommand = appointmentCommand;
    }

    @Override
    public void save(Appointment target) {

    }

    @Override
    public void remove(Long eventId, Family family) {

    }
}
