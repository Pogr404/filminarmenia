package com.registration.ex.gasstationexception;

import com.registration.ex.EntityNotFoundException;

public class GasStationNotFoundException extends EntityNotFoundException {

    public GasStationNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
