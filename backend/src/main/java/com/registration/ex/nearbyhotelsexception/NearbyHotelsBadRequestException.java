package com.registration.ex.nearbyhotelsexception;

import com.registration.ex.BadRequestException;

public class NearbyHotelsBadRequestException extends BadRequestException {
    public NearbyHotelsBadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
