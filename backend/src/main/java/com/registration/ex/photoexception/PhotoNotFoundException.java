package com.registration.ex.photoexception;

import com.registration.ex.EntityNotFoundException;

public class PhotoNotFoundException extends EntityNotFoundException {
    public PhotoNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
