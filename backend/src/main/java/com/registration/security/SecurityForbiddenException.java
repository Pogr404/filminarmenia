package com.registration.security;

import com.registration.ex.ForbiddenException;

public class SecurityForbiddenException extends ForbiddenException {
    public SecurityForbiddenException(String errorMessage) {
        super(errorMessage);
    }
}
