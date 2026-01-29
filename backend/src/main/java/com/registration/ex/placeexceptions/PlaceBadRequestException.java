package com.registration.ex.placeexceptions;

import com.registration.ex.BadRequestException;

public class PlaceBadRequestException extends BadRequestException {
    public PlaceBadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
