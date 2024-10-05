package com.huntercodexs.demo.handler.exception;

import com.huntercodexs.demo.handler.error.Help4DevsServiceError;
import lombok.Getter;

@Getter
@SuppressWarnings("serial")
public class Help4DevsServiceException extends RuntimeException {

	int codeError;
    int subCodeError;
    String message;
    public String tcn;

    public Help4DevsServiceException(Help4DevsServiceError cardsError) {
    	this.codeError = cardsError.getCodeError();
        this.subCodeError = cardsError.getSubCodeError();
        this.message = cardsError.getMessage();
    }

    public Help4DevsServiceException(Help4DevsServiceError cardsError, String message) {
    	this.codeError = cardsError.getCodeError();
        this.subCodeError = cardsError.getSubCodeError();
        this.message = message;
    }

    public Help4DevsServiceException(String tcn, Help4DevsServiceError cardsError) {
        this.codeError = cardsError.getCodeError();
        this.subCodeError = cardsError.getSubCodeError();
        this.message = cardsError.getMessage();
        this.tcn = tcn;
    }
    
}
