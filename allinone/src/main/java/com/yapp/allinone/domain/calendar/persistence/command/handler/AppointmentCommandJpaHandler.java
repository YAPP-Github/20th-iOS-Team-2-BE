package com.yapp.allinone.domain.calendar.persistence.command.handler;

import com.yapp.allinone.domain.calendar.persistence.repository.AppointmentJpaRepository;
import com.yapp.supporter.entity.calander.appointment.entity.Appointment;
import com.yapp.supporter.entity.family.persistence.entity.Family;
import org.springframework.stereotype.Component;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@Component
public class AppointmentCommandJpaHandler implements AppointmentCommandHandler {
    private final AppointmentJpaRepository appointmentJpaRepository;

    public AppointmentCommandJpaHandler(AppointmentJpaRepository appointmentJpaRepository) {
        this.appointmentJpaRepository = appointmentJpaRepository;
    }

    @Override
    public void save(Appointment target) {
        appointmentJpaRepository.save(target);
    }

    @Override
    public void remove(Long appointmentId, Family family) {
        appointmentJpaRepository.deleteByIdAndFamily(appointmentId, family);
    }
}
