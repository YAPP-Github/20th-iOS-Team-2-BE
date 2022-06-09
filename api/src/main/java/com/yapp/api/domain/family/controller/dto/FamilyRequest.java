package com.yapp.api.domain.family.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class FamilyRequest {
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Create {}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Modify {}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GreetWithEmoji {}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GreetWithMessage {}
}
