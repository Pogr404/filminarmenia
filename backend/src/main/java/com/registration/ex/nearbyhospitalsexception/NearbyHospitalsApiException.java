package com.registration.ex.nearbyhospitalsexception;

import com.registration.ex.ApiException;

public class NearbyHospitalsApiException extends ApiException {
    public NearbyHospitalsApiException(String errorMessage) {
        super(errorMessage);
    }

    public NearbyHospitalsApiException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
