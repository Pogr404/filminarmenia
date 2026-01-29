package com.registration.ex.countryexception;

import com.registration.ex.BadRequestException;

public class CountryBadRequestException extends BadRequestException {
    public CountryBadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
