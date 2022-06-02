package com.yapp.core.error;

import static org.springframework.http.HttpStatus.*;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yapp.core.error.exception.BaseBusinessException;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackages = "com.yapp")
public class GlobalExceptionHandler {

	@ExceptionHandler(BaseBusinessException.class)
	public ResponseEntity<ErrorResponse> handlerBaseBusinessException(BaseBusinessException e) {

		return ResponseEntity.status(e.getStatus())
							 .body(convertToErrorResponse(e.getStatus(), e.getDetail()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleInternalException(Exception e) {

		/** slack 연결 해야함 **/

		log.error("INTERNAL ERROR");
		e.printStackTrace();

		return ResponseEntity.internalServerError()
							 .body(convertToErrorResponse(INTERNAL_SERVER_ERROR.value(), e.toString()));
	}

	private ErrorResponse convertToErrorResponse(int status, String detail) {
		return ErrorResponse.builder()
							.status(status)
							.detail(detail)
							.build();
	}

	@Getter
	private static class ErrorResponse {
		private final LocalDateTime timestamp;
		private final int status;
		private final String detail;

		@Builder
		public ErrorResponse(int status, String detail) {
			this.timestamp = LocalDateTime.now();
			this.status = status;
			this.detail = detail;
		}
	}
}
