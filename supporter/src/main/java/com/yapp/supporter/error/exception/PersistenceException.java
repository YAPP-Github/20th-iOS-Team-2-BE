package com.yapp.supporter.error.exception;

/**
 * Author : daehwan2yo
 * Date : 2022/08/07
 * Info :
 **/
public class PersistenceException extends RuntimeException {
    private final ErrorCode errorCode;

    public PersistenceException(ErrorCode errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
