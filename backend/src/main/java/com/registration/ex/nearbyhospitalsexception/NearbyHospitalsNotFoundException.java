package com.registration.ex.nearbyhospitalsexception;

import com.registration.ex.EntityNotFoundException;

public class NearbyHospitalsNotFoundException extends EntityNotFoundException {
    public NearbyHospitalsNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
