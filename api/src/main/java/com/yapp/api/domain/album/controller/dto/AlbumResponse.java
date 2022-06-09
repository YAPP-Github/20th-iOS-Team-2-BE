package com.yapp.api.domain.album.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class AlbumResponse {
	@Getter
	@NoArgsConstructor
	public static class List {}

	@Getter
	@NoArgsConstructor
	public static class DetailsAsDate {}

	@Getter
	@NoArgsConstructor
	public static class DetailsAsKind {}

	@Getter
	@NoArgsConstructor
	public static class UploadFiles {}

	@Getter
	@NoArgsConstructor
	public static class Whether {}

	@Getter
	@NoArgsConstructor
	public static class Comments {}
}
