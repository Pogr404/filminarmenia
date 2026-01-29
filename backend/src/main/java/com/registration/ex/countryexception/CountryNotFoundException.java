package com.registration.ex.countryexception;

import com.registration.ex.EntityNotFoundException;

public class CountryNotFoundException extends EntityNotFoundException {
    public CountryNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
