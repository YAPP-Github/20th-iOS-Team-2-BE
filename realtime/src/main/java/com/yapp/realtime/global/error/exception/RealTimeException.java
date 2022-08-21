package com.yapp.realtime.global.error.exception;

import com.yapp.supporter.error.exception.ErrorCode;

/**
 * Author : daehwan2yo
 * Date : 2022/08/22
 * Info :
 **/
public class RealTimeException extends RuntimeException {
    private final ErrorCode errorCode;

    public RealTimeException(ErrorCode errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;
    }

    public RealTimeException(ErrorCode errorCode, String location) {
        super(location + ", " + errorCode.getDetail());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
