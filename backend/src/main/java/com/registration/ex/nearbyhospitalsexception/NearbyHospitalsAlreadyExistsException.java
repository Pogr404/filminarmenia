package com.registration.ex.nearbyhospitalsexception;

import com.registration.ex.ResourceAlreadyExistException;

public class NearbyHospitalsAlreadyExistsException extends ResourceAlreadyExistException {
    public NearbyHospitalsAlreadyExistsException(String message) {
        super(message);
    }
}
