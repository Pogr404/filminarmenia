package com.registration.ex.placetypeexception;

import com.registration.ex.ResourceAlreadyExistException;

public class PlaceTypeAlreadyExistsException extends ResourceAlreadyExistException {
    public PlaceTypeAlreadyExistsException(String message) {
        super(message);
    }
}
