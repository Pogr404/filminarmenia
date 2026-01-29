package com.registration.ex.nearbyhotelsexception;

import com.registration.ex.EntityNotFoundException;

public class NearbyHotelsNotFoundException extends EntityNotFoundException {
    public NearbyHotelsNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
