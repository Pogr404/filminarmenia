package com.registration.ex.gasstationexception;

import com.registration.ex.BadRequestException;

public class GasStationBadRequestException extends BadRequestException {
    public GasStationBadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
