package com.registration.ex.climatezoneexception;

import com.registration.ex.EntityNotFoundException;

public class ClimateZoneNotFoundException extends EntityNotFoundException {
    public ClimateZoneNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
