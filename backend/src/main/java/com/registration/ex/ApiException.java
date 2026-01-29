package com.registration.ex;

public abstract class ApiException extends RuntimeException {

    public ApiException(String errorMessage) {
        super(errorMessage);
    }

    public ApiException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
