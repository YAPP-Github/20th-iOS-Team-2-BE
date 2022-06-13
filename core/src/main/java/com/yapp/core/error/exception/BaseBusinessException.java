package com.yapp.core.error.exception;

import lombok.Getter;

@Getter
public class BaseBusinessException extends RuntimeException {
	private final ErrorCode errorCode;

	public BaseBusinessException(ErrorCode errorCode, Exception e) {
		super(errorCode.getDetail());
		errorCode.setMessage(e.toString());
		this.errorCode = errorCode;
	}

	public final int getStatus() {
		return errorCode.getStatus();
	}

	public final String getDetail() {
		return errorCode.getDetail();
	}
}
