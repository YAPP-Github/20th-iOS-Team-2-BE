package com.yapp.api.domain.family.controller.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class FamilyRequest {
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Create {
		@NotBlank
		private String familyName;
		@NotBlank
		private String familyMotto;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Modify {
		private String imageLink;
		private String familyName;
		private String familyMotto;
		private List<Nickname> nicknames = new ArrayList<>();

		@NoArgsConstructor
		@AllArgsConstructor
		@Getter
		@Setter
		public class Nickname {
			private String pastNickname;
			private String newNickname;
		}
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GreetWithEmoji {
		private Integer emojiNumber;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GreetWithMessage {
		private String content;
	}
}
