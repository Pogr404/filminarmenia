package com.registration.ex.photo360exception;

import com.registration.ex.EntityNotFoundException;

public class Photo360NotFoundException extends EntityNotFoundException {
    public Photo360NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
