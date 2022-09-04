package com.yapp.api.global.error;

import com.yapp.api.global.error.exception.ApiException;
import com.yapp.supporter.error.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException e) {
        log.error("[Api error] : {}", e.getMessage());

        return ResponseEntity.status(e.getErrorCode()
                        .getStatus())
                .body(ErrorResponse.from(e.getErrorCode()));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return ResponseEntity.status(status).body(ErrorResponse.noRequestBody());
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
