package com.yapp.core.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
	FILE_NOT_FOUND(404, "없는 파일 리소스에 대한 접근입니다."),
	ALBUM_NOT_FOUND(404, "없는 앨범 리소스에 대한 접근입니다."),
	COMMENT_NOT_FOUND(404, "없는 댓글 리소스에 대한 접근입니다."),
	APPOINTMENT_NOT_FOUND(404, "없는 일정 리소스에 대한 접근입니다."),
	FILE_IO_ERROR(500, "파일 업로드에 문제가 발생했습니다.");

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
