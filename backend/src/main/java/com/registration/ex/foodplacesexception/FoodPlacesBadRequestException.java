package com.registration.ex.foodplacesexception;

import com.registration.ex.BadRequestException;

public class FoodPlacesBadRequestException extends BadRequestException {
    public FoodPlacesBadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
