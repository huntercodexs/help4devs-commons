package com.huntercodexs.demo.handler.exception;

import com.huntercodexs.demo.handler.error.Help4DevsSampleError;
import lombok.Getter;

@Getter
@SuppressWarnings("serial")
public class Help4DevsSampleException extends RuntimeException {

	int codeError;
    int subCodeError;
    String message;
    public String tcn;

    public Help4DevsSampleException(Help4DevsSampleError cardsError) {
    	this.codeError = cardsError.getCodeError();
        this.subCodeError = cardsError.getSubCodeError();
        this.message = cardsError.getMessage();
    }

    public Help4DevsSampleException(Help4DevsSampleError cardsError, String message) {
    	this.codeError = cardsError.getCodeError();
        this.subCodeError = cardsError.getSubCodeError();
        this.message = message;
    }

    public Help4DevsSampleException(String tcn, Help4DevsSampleError cardsError) {
        this.codeError = cardsError.getCodeError();
        this.subCodeError = cardsError.getSubCodeError();
        this.message = cardsError.getMessage();
        this.tcn = tcn;
    }
    
}
