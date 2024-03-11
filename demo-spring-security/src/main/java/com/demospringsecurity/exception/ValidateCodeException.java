package com.demospringsecurity.exception;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = 5405847617476917015L;

    public ValidateCodeException(String message) {
        super(message);
    }
}
