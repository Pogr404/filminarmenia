package com.registration.ex.nearbyhotelsexception;

import com.registration.ex.ResourceAlreadyExistException;

public class NearbyHotelsAlreadyExistsException extends ResourceAlreadyExistException {
    public NearbyHotelsAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
