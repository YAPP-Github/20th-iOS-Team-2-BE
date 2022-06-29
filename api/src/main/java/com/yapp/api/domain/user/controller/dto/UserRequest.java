package com.yapp.api.domain.user.controller.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserRequest {
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Create {
		private String name;
		private String roleInFamily;
		private LocalDate birthday;
		private String nickname;
	}
}
