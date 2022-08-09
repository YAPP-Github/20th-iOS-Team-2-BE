package com.yapp.api.domain.home;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.yapp.core.persistence.calander.appointment.persistence.entity.Appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Author : daehwan2yo
 * Date : 2022/08/03
 * Info : 
 **/
public class HomeResponse {

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Info {
		private String familyName;
		private List<EventInfo> events = new ArrayList<>();

		@Getter
		@NoArgsConstructor
		@AllArgsConstructor
		public static class EventInfo {
			private Long eventId;
			private String title;
			private LocalDate date;
			private String color;

			public static EventInfo from(Appointment appointment) {
				return new EventInfo(appointment.getId(), appointment.getTitle(), appointment.getDate(), appointment.getColor());
			}
		}
	}
}
