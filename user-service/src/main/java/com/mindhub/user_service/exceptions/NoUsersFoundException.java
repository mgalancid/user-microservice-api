package com.mindhub.user_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoUsersFoundException extends RuntimeException {
    public NoUsersFoundException() {
        super("Users were not found");
    }
    public NoUsersFoundException(String message) {
        super(message);
    }
}
