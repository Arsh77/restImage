package com.crossover.assignment.restImage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ImproperRequestException extends RuntimeException {

    public ImproperRequestException() {
        super();
    }

    public ImproperRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ImproperRequestException(final String message) {
        super(message);
    }

    public ImproperRequestException(final Throwable cause) {
        super(cause);
    }

}