package com.yapp.api.global.error.exception;

import com.yapp.core.error.exception.ErrorCode;

/**
 * Author : daehwan2yo
 * Date : 2022/08/10
 * Info :
 **/
public class ApiException extends RuntimeException {
    private final ErrorCode errorCode;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;
    }

    public ApiException(ErrorCode errorCode, String location) {
        super(location + ", " + errorCode.getDetail());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
