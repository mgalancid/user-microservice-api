package com.mindhub.user_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NON_AUTHORITATIVE_INFORMATION)
public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException() {
        super("Role cannot be found");
    }
    public RoleNotFoundException(String message) {
        super(message);
    }
}
