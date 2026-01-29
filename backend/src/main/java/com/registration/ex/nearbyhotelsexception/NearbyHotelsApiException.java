package com.registration.ex.nearbyhotelsexception;

import com.registration.ex.ApiException;

public class NearbyHotelsApiException extends ApiException {
    public NearbyHotelsApiException(String errorMessage) {
        super(errorMessage);
    }

    public NearbyHotelsApiException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
