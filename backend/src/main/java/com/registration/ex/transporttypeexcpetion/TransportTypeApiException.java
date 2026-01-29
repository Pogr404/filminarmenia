package com.registration.ex.transporttypeexcpetion;

import com.registration.ex.ApiException;

public class TransportTypeApiException extends ApiException {
    public TransportTypeApiException(String errorMessage) {
        super(errorMessage);
    }

    public TransportTypeApiException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
