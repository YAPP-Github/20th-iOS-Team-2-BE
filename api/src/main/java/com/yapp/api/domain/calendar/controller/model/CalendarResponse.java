package com.yapp.api.domain.calendar.controller.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.yapp.core.entity.calander.appointment.entity.Appointment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CalendarResponse {

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AsMonth {
		List<DayInfo> dates = new ArrayList<>();

		public static AsMonth of(List<Appointment> retrieveAsMonth) {
			return new AsMonth(retrieveAsMonth.stream()
											  .map(DayInfo::of)
											  .collect(Collectors.toList()));
		}
	}

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class DayInfo {
		private String date;
		private String color;

		public static DayInfo of(Appointment appointment) {
			return new DayInfo(appointment.getDate()
										  .format(DateTimeFormatter.ISO_DATE), appointment.getColor());
		}
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AsDay {
		List<DayDetail> events = new ArrayList<>();

		public static AsDay from(List<Appointment> appointments) {
			return new AsDay(appointments.stream()
										 .map(DayDetail::of)
										 .collect(Collectors.toList()));
		}
	}

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class DayDetail {
		private Long planId;
		private String title;
		private String time;
		private String color;

		public static DayDetail of(Appointment appointment) {
			return new DayDetail(appointment.getId(),
								 appointment.getTitle(),
								 appointment.getTime()
											.format(DateTimeFormatter.ISO_TIME),
								 appointment.getColor());
		}
	}
}
