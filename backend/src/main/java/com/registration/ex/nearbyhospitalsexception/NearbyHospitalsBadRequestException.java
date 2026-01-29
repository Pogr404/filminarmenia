package com.registration.ex.nearbyhospitalsexception;

import com.registration.ex.BadRequestException;

public class NearbyHospitalsBadRequestException extends BadRequestException {
    public NearbyHospitalsBadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
