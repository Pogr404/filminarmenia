package com.registration.ex.gasstationexception;

import com.registration.ex.ApiException;

public class GasStationApiException extends ApiException {
    public GasStationApiException(String errorMessage) {
        super(errorMessage);
    }

    public GasStationApiException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
