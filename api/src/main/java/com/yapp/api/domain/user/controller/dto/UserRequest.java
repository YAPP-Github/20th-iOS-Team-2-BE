package com.yapp.api.domain.user.controller.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
		private LocalDate birthDay;
		private String nickname;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Modify {
		private String nickname;
		private LocalDate birthDay;
		private String imageLink;
		private String roleInFamily;
	}
}
