package com.registration.ex.nearbypharmacyexception;

import com.registration.ex.EntityNotFoundException;

public class NearbyPharmacyNotFoundException extends EntityNotFoundException {
    public NearbyPharmacyNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
