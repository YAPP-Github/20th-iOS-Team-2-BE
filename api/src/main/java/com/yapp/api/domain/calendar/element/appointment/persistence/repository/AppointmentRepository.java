package com.yapp.api.domain.calendar.element.appointment.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yapp.api.domain.calendar.element.appointment.persistence.entity.Appointment;
import com.yapp.api.domain.family.persistence.entity.Family;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	Optional<Appointment> findByIdAndTitle(Long appointmentId, String title);

	@Query(nativeQuery = true,
		   value = "SELECT appointment FROM Appointment appointment " + "WHERE appointment.family=:family "
			   + "AND DATE_FORMAT(appointment.date, '%Y-%M')=:dateUtilMonth")
	List<Appointment> findByFamilyAndDateUntilMonth(Family family, String dateUtilMonth);

	Optional<Appointment> findByFamilyAndDate(Family family, String date);
}
