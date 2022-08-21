package com.yapp.api.domain.calendar.persistence.repository;

import com.yapp.supporter.entity.calander.appointment.entity.Appointment;
import com.yapp.supporter.entity.family.persistence.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Author : daehwan2yo
 * Date : 2022/08/14
 * Info :
 **/
public interface AppointmentJpaRepository extends JpaRepository<Appointment, Long> {
    void deleteByIdAndFamily(Long appointmentId, Family family);

    @Query(nativeQuery = true, value = "SELECT * FROM appointment WHERE family_id=:familyId AND DATE_FORMAT(date, '%Y-%m')=:yearMonth")
    List<Appointment> findAllByFamilyAndYearMonth(Long familyId, String yearMonth);

    Optional<Appointment> findByFamilyAndDate(Family family, LocalDate date);

    List<Appointment> findAllByFamilyAndDate(Family family, LocalDate date);

    List<Appointment> findAllByFamilyAndDateBetween(Family family, LocalDate now, LocalDate plusMonths);
}
