package com.registration.ex.climatezoneexception;

import com.registration.ex.ApiException;

public class ClimateZoneApiException extends ApiException {
    public ClimateZoneApiException(String errorMessage) {
        super(errorMessage);
    }

    public ClimateZoneApiException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
