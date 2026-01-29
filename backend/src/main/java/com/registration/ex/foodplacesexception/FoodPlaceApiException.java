package com.registration.ex.foodplacesexception;

import com.registration.ex.ApiException;

public class FoodPlaceApiException extends ApiException {
    public FoodPlaceApiException(String errorMessage) {
        super(errorMessage);
    }

    public FoodPlaceApiException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
