package com.registration.ex.placetypeexception;

import com.registration.ex.ApiException;

public class PlaceTypeApiException extends ApiException {
    public PlaceTypeApiException(String errorMessage) {
        super(errorMessage);
    }

    public PlaceTypeApiException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
