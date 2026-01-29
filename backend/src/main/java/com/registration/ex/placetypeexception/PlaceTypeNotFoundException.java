package com.registration.ex.placetypeexception;

import com.registration.ex.EntityNotFoundException;

public class PlaceTypeNotFoundException extends EntityNotFoundException {
    public PlaceTypeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
