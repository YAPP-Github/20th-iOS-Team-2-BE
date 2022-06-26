package com.yapp.api.domain.calendar.controller.dto;

import java.util.ArrayList;
import java.util.List;

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
	}

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class DayInfo {
		private String date;
		private String color;

		public static DayInfo of(String date, String color) {
			return new DayInfo(date, color);
		}
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AsDay {
		List<DayDetail> events = new ArrayList<>();
	}

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class DayDetail {
		private Long planId;
		private String title;
		private String time;
		private String color;

		public static DayDetail of(Long planId, String title, String time, String color) {
			return new DayDetail(planId, title, time, color);
		}
	}
}
