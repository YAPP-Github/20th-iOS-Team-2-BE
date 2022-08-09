package com.yapp.core.error.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ErrorResponse {
	private final LocalDateTime timestamp;
	private final int status;
	private final String detail;

	public ErrorResponse(BaseBusinessException e) {
		this.timestamp = LocalDateTime.now();
		this.status = e.getStatus();
		this.detail = e.getDetail();
	}

	public ErrorResponse(Exception e) {
		this.timestamp = LocalDateTime.now();
		this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
		this.detail = e.toString();
	}
}
