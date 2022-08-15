package com.yapp.api.domain.calendar.persistence.command.handler;

import com.yapp.core.entity.calander.appointment.entity.Appointment;
import com.yapp.core.entity.family.persistence.entity.Family;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface AppointmentCommandHandler {

    void save(Appointment target);

    void remove(Long eventId, Family family);
}
