package com.yapp.api.global.error;

import com.yapp.api.global.error.exception.ApiException;
import com.yapp.core.error.exception.PersistenceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.yapp.core.error.response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public ErrorResponse handleApiException(ApiException e) {
		log.error("Api error : {}", e.toString());

		return ErrorResponse.from(e.getErrorCode());
	}

	@ExceptionHandler(PersistenceException.class)
	public ErrorResponse handlePersistenceException(PersistenceException e) {
		log.error("Persistence error : {} ", e.toString());

		return ErrorResponse.from(e.getErrorCode());
	}

	@ExceptionHandler(Exception.class)
	public ErrorResponse handleInternalException(Exception e) {

		/** slack 연결 해야함 **/

		log.error("Internal error : {}", e.toString());
		e.printStackTrace();

		return ErrorResponse.internal(e.getMessage());
	}
}
