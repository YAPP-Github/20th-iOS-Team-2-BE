package com.yapp.core.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	;

	private final int status;
	private final String detail;
}
