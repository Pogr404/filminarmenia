package com.registration.ex.countryexception;


import com.registration.ex.ResourceAlreadyExistException;

public class CountryAlreadyExistException extends ResourceAlreadyExistException {
    public CountryAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
