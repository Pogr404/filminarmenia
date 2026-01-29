package com.registration.ex.countryexception;

import com.registration.ex.ApiException;

public class CountryApiException extends ApiException {
    public CountryApiException(String errorMessage) {
        super(errorMessage);
    }

    public CountryApiException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
