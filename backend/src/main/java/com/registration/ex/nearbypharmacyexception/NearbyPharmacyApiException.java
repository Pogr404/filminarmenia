package com.registration.ex.nearbypharmacyexception;

import com.registration.ex.ApiException;

public class NearbyPharmacyApiException extends ApiException {
    public NearbyPharmacyApiException(String errorMessage) {
        super(errorMessage);
    }

    public NearbyPharmacyApiException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
