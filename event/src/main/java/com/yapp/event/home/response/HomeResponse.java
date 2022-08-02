package com.yapp.event.home.response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.yapp.core.persistance.calander.appointment.persistence.entity.Appointment;
import com.yapp.core.persistance.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Author : daehwan2yo
 * Date : 2022/07/31
 * Info : 
 **/
public class HomeResponse {

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class HomeStatusInfo {
		private List<HomeMemberInfo> members = new ArrayList<>();
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class HomeMemberInfo {
		private Long userId;
		private String image;
		private String nickname;
		private String role;
		private String updatedAt;
		private int emoji;
		private String content;

		public static HomeMemberInfo of(User user, User ori) {
			return new HomeMemberInfo(user.getId(),
									  user.imageLink(),
									  user.getNicknameForUser(ori),
									  user.getRoleInFamily(),
									  user.getContentLastModified()
										  .format(DateTimeFormatter.ISO_DATE_TIME),
									  user.getEmoji(),
									  user.getContent());
		}
	}

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
			private String title;
			private LocalDate date;
			private String color;

			public static EventInfo from(Appointment appointment) {
				return new EventInfo(appointment.getTitle(), appointment.getDate(), appointment.getColor());
			}
		}
	}
}
