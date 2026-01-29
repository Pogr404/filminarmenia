package com.registration.ex.transporttypeexcpetion;

import com.registration.ex.EntityNotFoundException;

public class TransportTypeNotFoundException extends EntityNotFoundException {
    public TransportTypeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
