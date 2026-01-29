package com.registration.ex.placeexceptions;

import com.registration.ex.EntityNotFoundException;

public class PlaceNotFoundException extends EntityNotFoundException {

    public PlaceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
