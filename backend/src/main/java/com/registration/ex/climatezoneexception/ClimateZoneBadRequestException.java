package com.registration.ex.climatezoneexception;

import com.registration.ex.BadRequestException;

public class ClimateZoneBadRequestException extends BadRequestException {
    public ClimateZoneBadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
