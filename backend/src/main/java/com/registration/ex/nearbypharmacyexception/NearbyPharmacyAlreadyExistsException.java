package com.registration.ex.nearbypharmacyexception;

import com.registration.ex.ResourceAlreadyExistException;

public class NearbyPharmacyAlreadyExistsException extends ResourceAlreadyExistException {
    public NearbyPharmacyAlreadyExistsException(String message) {
        super(message);
    }
}
