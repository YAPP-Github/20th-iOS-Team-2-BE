package com.yapp.core.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
	BEARER_TOKEN_INVALID(400, "토큰이 비정상적입니다."),
	BEARER_TOKEN_EXPIRED(400, "토큰이 만료되었습니다."),
	OAUTH_KIND_ERROR(400, "허가되지 않는 OAuth 인증 타입으로 인증을 시도했습니다."),
	USER_NOT_FOUND_FAMILY(400, "회원이 가족에 소속되어있지 않습니다."),
	ALBUM_FILE_NOT_MATCH(400, "앨범에 종속되지 않은 파일을 대표로 설정하려고 시도했습니다."),
	CONNECTION_PREFIX_ERROR(400, "ws 연결 시 정의되지 않은 prefix로 접근을 시도했습니다."),
	CONNECTION_RESOURCE_ERROR(400, "ws 연결 시 올바르지 못한 familyId 로 접근을 시도했습니다."),
	ALREADY_JOINED(400, "이미 가족에 속해있는 회원입니다."),
	FULL_MEMBER(400, "가족의 회원수가 가득 찼습니다. (10명)"),
	NO_AUTHENTICATION_ACCESS(401, "인증이 필요합니다."),
	USER_NOT_FOUND(400, "없는 회원에 대한 접근입니다."),

	NOT_VALID_CODE(403, "유효하지 않는 코드의 접근입니다."),

	FILE_NOT_FOUND(404, "없는 파일 리소스에 대한 접근입니다."),
	ALBUM_NOT_FOUND(404, "없는 앨범 리소스에 대한 접근입니다."),
	COMMENT_NOT_FOUND(404, "없는 댓글 리소스에 대한 접근입니다."),
	APPOINTMENT_NOT_FOUND(404, "없는 일정 리소스에 대한 접근입니다."),
	FAMILY_NOT_FOUND(404, "없는 가족 리소스에 대한 접근입니다."),
	TOKEN_IS_BLANK(404, "헤더는 존재하나, 토큰이 들어있지 않습니다."),

	BEARER_INTERNAL_ERROR(500, "토큰 검증과정에서 에러가 발생했습니다."),
	FILE_IO_ERROR(500, "파일 업로드에 문제가 발생했습니다."),
	OAUTH_ERROR(500, "OAuth 인증과정에서 문제가 발생했습니다. (유효하지 않는 accessToken)"),;

	private final int status;
	private final String detail;
	private String message;

	ErrorCode(int status, String detail) {
		this.status = status;
		this.detail = detail;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
