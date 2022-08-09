package com.yapp.core.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.yapp.core.error.response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BaseBusinessException.class)
	public ResponseEntity<ErrorResponse> handlerBaseBusinessException(BaseBusinessException e) {

		log.error("custom error : {} ",e.getErrorCode().getMessage());
		return ResponseEntity.status(e.getStatus())
							 .body(new ErrorResponse(e));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleInternalException(Exception e) {

		/** slack 연결 해야함 **/

		log.error("INTERNAL ERROR");
		e.printStackTrace();

		return ResponseEntity.internalServerError()
							 .body(new ErrorResponse(e));
	}
}
