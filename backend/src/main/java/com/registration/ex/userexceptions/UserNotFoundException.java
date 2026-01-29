package com.registration.ex.userexceptions;

import com.registration.ex.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
