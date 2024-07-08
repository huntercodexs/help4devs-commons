package com.huntercodexs.demo.handler.error;

import lombok.Getter;

@Getter
public enum Help4DevsServiceError {

	HELP4DEVS_SAMPLE_ERROR(1,1,"Any Error Description"),
	HELP4DEVS_UNKNOWN_ERROR(999,999,"Unknown error");

	private final int codeError;
    private final int subCodeError;
    private final String message;

	Help4DevsServiceError(int codeError, int subCodeError, String message) {
		this.codeError = codeError;
		this.subCodeError = subCodeError;
		this.message = message;
	}
	
}
