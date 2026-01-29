package com.registration.ex.roadqualityexception;

import com.registration.ex.ApiException;

public class RoadQualityApiException extends ApiException {
    public RoadQualityApiException(String errorMessage) {
        super(errorMessage);
    }

    public RoadQualityApiException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
