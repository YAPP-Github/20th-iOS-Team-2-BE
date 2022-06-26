package com.yapp.api.domain.calendar.controller.dto;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CalendarRequest {

	@Getter
	@Service
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Create {
		private boolean allDay;
		private String date;
		private String time;
		private String title;
		private String content;
		private boolean visibility;
		private String color;
	}

	@Getter
	@Service
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Modify {
		private String color;
		private boolean allDay;
		private String date;
		private String time;
		private String content;
		private String title;
	}
}
