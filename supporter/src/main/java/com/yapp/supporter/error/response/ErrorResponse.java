package com.yapp.supporter.error.response;

import com.yapp.supporter.error.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ErrorResponse {
    private final String timestamp;
    private final int status;
    private final String detail;

    @Builder
    private ErrorResponse(int status, String detail) {
        this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.status = status;
        this.detail = detail;
    }

    public static ErrorResponse from(ErrorCode e) {
        return ErrorResponse.builder()
                .status(e.getStatus())
                .detail(e.getDetail())
                .build();
    }

    public static ErrorResponse validError() {
        return ErrorResponse.builder()
                .status(400)
                .detail("null 혹은 잘못된 값이 binding 되었습니다. 입력 값을 확인해주세요.")
                .build();
    }

    public static ErrorResponse internal(String message) {
        return ErrorResponse.builder()
                .status(500)
                .detail(message)
                .build();
    }

    public static ErrorResponse bindError() {
        return ErrorResponse.builder()
                .status(400)
                .detail("bind 에서 에러가 발생했습니다. 필수 query param 이 누락되었는지 확인하세요.")
                .build();
    }

    public static ErrorResponse missingParameterError() {
        return ErrorResponse.builder()
                .status(400)
                .detail("Request 에 바인딩하지 못하는 파라미터가 입력되었습니다. Params 를 확인하세요.")
                .build();
    }
}
