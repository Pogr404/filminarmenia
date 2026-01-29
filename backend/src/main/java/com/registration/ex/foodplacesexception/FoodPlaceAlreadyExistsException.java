package com.registration.ex.foodplacesexception;

import com.registration.ex.ResourceAlreadyExistException;

public class FoodPlaceAlreadyExistsException extends ResourceAlreadyExistException {
    public FoodPlaceAlreadyExistsException(String message) {
        super(message);
    }
}
