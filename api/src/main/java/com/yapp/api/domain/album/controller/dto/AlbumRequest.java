package com.yapp.api.domain.album.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AlbumRequest {
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Upload {}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Modify {}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Comment {}
}
