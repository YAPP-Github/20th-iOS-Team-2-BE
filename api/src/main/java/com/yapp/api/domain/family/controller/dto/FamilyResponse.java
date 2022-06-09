package com.yapp.api.domain.family.controller.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FamilyResponse {
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Create {
		private Long familyId;
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class HomeInfo {
		private String familyName;
		private List<EventInfo> events = new ArrayList<>();

		@Getter
		@NoArgsConstructor
		@AllArgsConstructor
		public static class EventInfo {
			private String title;
			private String eventDate;
		}
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Notifications {
		private List<Notification> notifications = new ArrayList<>();

		@Getter
		@NoArgsConstructor
		@AllArgsConstructor
		public static class Notification {
			private String type;
			private String content;
			private Long targetId;
			private String createdDate;
		}
	}
}
