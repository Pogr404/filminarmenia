package com.registration.ex.foodplacesexception;

import com.registration.ex.EntityNotFoundException;

public class FoodPlaceNotFoundException extends EntityNotFoundException {
    public FoodPlaceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
