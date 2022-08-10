package com.yapp.core.error.response;

import java.time.LocalDateTime;

import com.yapp.core.error.exception.ErrorCode;
import com.yapp.core.error.exception.PersistenceException;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ErrorResponse {
	private final LocalDateTime timestamp;
	private final int status;
	private final String detail;

	@Builder
	private ErrorResponse(int status, String detail) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.detail = detail;
	}

	public static ErrorResponse from(ErrorCode e) {
		return ErrorResponse.builder()
				.status(e.getStatus())
				.detail(e.getDetail())
				.build();
	}

	public static ErrorResponse internal(String message) {
		return ErrorResponse.builder()
				.status(500)
				.detail(message)
				.build();
	}
}
