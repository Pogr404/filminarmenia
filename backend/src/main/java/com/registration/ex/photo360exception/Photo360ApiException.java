package com.registration.ex.photo360exception;

import com.registration.ex.ApiException;

public class Photo360ApiException extends ApiException {
    public Photo360ApiException(String errorMessage) {
        super(errorMessage);
    }

    public Photo360ApiException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
