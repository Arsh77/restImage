package com.crossover.assignment.restImage.message;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
	
	private int errorCode;
    private HttpStatus status;
    private String message;

    public ErrorMessage(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
        this.setErrorCode(status.value());
    }

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
    
}
