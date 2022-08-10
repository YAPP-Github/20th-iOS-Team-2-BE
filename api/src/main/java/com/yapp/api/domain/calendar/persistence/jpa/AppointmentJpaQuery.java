package com.yapp.api.domain.calendar.persistence.jpa;

import com.yapp.api.domain.calendar.persistence.AppointmentQuery;
import com.yapp.core.entity.calander.appointment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public interface AppointmentJpaQuery extends JpaRepository<Appointment, Long>, AppointmentQuery {
    // @Query(nativeQuery = true, value = "SELECT * FROM appointment " + "WHERE family_id=:family " + "AND DATE_FORMAT(date, '%Y-%m')=:dateUtilMonth")
    //    List<Appointment> findByFamilyAndDateUntilMonth(Long family, String dateUtilMonth);
}
