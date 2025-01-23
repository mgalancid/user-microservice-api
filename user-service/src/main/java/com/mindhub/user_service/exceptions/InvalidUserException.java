package com.mindhub.user_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class InvalidUserException extends RuntimeException {
    public InvalidUserException() {
        super("Error during creation of an order");
    }
    public InvalidUserException(String message) {
        super(message);
    }
}
