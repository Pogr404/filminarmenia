package com.registration.ex.photoexception;

import com.registration.ex.ApiException;

public class PhotoApiException extends ApiException {
    public PhotoApiException(String errorMessage) {
        super(errorMessage);
    }

    public PhotoApiException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
