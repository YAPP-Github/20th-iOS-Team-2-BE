package com.yapp.api.domain.calendar.persistence.query.repository;

import com.yapp.core.entity.calander.appointment.entity.Appointment;
import com.yapp.core.entity.family.persistence.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface AppointmentQuery extends JpaRepository<Appointment, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM appointment " + "WHERE family_id=:family " + "AND DATE_FORMAT(date, '%Y-%m')=:dateUtilMonth")
    List<Appointment> findByFamilyAndDateUntilMonth(Long family, String dateUtilMonth);

    Optional<Appointment> findByIdAndTitle(Long appointmentId, String title);

    Optional<Appointment> findByFamilyAndDate(Family family, LocalDate date);

    List<Appointment> findAllByFamilyAndDate(Family family, LocalDate date);

    List<Appointment> findAllByFamilyAndDateBetween(Family family, LocalDate now, LocalDate plusMonths);
}
