package com.registration.ex.userexceptions;

import com.registration.ex.ResourceAlreadyExistException;

public class UserAlreadyExistException extends ResourceAlreadyExistException {
    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
