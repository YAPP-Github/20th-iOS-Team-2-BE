package com.yapp.core.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
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
