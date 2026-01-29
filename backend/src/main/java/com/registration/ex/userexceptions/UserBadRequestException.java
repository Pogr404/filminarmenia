package com.registration.ex.userexceptions;

import com.registration.ex.BadRequestException;

public class UserBadRequestException extends BadRequestException {

    public UserBadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
