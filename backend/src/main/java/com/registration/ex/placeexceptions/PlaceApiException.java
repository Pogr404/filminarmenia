package com.registration.ex.placeexceptions;

import com.registration.ex.ApiException;

public class PlaceApiException extends ApiException {
    public PlaceApiException(String errorMessage) {
        super(errorMessage);
    }

    public PlaceApiException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
