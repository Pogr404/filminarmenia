package com.registration.ex.climatezoneexception;

import com.registration.ex.ResourceAlreadyExistException;

public class ClimateZoneAlreadyExistException extends ResourceAlreadyExistException {
    public ClimateZoneAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
