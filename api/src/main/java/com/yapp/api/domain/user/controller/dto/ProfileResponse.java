package com.yapp.api.domain.user.controller.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProfileResponse {
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MessageHistory {
		private String nickname;
		private String roleInFamily;
		private String profileLink;
		private int count;
		private List<MessageDetail> history = new ArrayList<>();

		@Getter
		@NoArgsConstructor
		@AllArgsConstructor
		public static class MessageDetail {
			private Long messageId;
			private String content;
			private String createdDate;
		}
	}
}
