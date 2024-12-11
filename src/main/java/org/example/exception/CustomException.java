package org.example.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private HttpStatus httpStatus;

    public CustomException(final String message) {
        super(message);
    }

    public CustomException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
