package com.huntercodexs.demo.handler.error;

import lombok.Getter;

@Getter
public enum Help4DevsSampleError {

	HELP4DEVS_SERVICE_SAMPLE_ERROR(1,1,"Any Error Description"),
	HELP4DEVS_SERVICE_UNKNOWN_ERROR(999,999,"Unknown error");

	private final int codeError;
    private final int subCodeError;
    private final String message;

	Help4DevsSampleError(int codeError, int subCodeError, String message) {
		this.codeError = codeError;
		this.subCodeError = subCodeError;
		this.message = message;
	}
	
}
