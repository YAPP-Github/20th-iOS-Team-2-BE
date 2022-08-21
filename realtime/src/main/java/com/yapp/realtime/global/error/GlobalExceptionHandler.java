package com.yapp.realtime.global.error;

import com.yapp.supporter.error.response.ErrorResponse;
import com.yapp.realtime.global.error.exception.RealTimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Author : daehwan2yo
 * Date : 2022/08/22
 * Info :
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RealTimeException.class)
    public ResponseEntity<ErrorResponse> handleApiException(RealTimeException e) {
        log.error("[RealTime error] : {}", e.getMessage());

        return ResponseEntity.status(e.getErrorCode()
                        .getStatus())
                .body(ErrorResponse.from(e.getErrorCode()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return ResponseEntity.status(status)
                .body(ErrorResponse.validError());
    }

    @Override
    protected ResponseEntity<Object> handleBindException(
            BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return ResponseEntity.status(status)
                .body(ErrorResponse.bindError());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return ResponseEntity.status(status)
                .body(ErrorResponse.missingParameterError());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleInternalException(Exception e) {

        /** slack 연결 해야함 **/

        log.error("Internal error : {}", e.toString());
        e.printStackTrace();

        return ErrorResponse.internal(e.getMessage());
    }
}
