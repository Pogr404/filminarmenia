package com.registration.ex.userexceptions;

import com.registration.ex.ApiException;

public class UserApiException extends ApiException {

    public UserApiException(String errorMessage) {
        super(errorMessage);
    }

    public UserApiException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
