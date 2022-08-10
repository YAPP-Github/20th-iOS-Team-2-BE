package com.yapp.api.domain.calendar.persistence;

import com.yapp.core.entity.family.persistence.entity.Family;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface AppointmentCommand {
    void deleteByIdAndFamily(Long eventId, Family family);
}
