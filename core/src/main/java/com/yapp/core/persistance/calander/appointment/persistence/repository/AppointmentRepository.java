package com.yapp.core.persistance.calander.appointment.persistence.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yapp.core.persistance.calander.appointment.persistence.entity.Appointment;
import com.yapp.core.persistance.family.persistence.entity.Family;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	Optional<Appointment> findByIdAndTitle(Long appointmentId, String title);

	@Query(nativeQuery = true,
		   value = "SELECT * FROM appointment " + "WHERE family_id=:family "
			   + "AND DATE_FORMAT(date, '%Y-%m')=:dateUtilMonth")
	List<Appointment> findByFamilyAndDateUntilMonth(Long family, String dateUtilMonth);

	Optional<Appointment> findByFamilyAndDate(Family family, LocalDate date);

	List<Appointment> findAllByFamilyAndDate(Family family, LocalDate date);

	List<Appointment> findAllByFamilyAndDateBetween(Family family, LocalDate now,LocalDate plusMonths);
}
