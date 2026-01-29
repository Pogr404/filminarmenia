package com.registration.ex.roadqualityexception;

import com.registration.ex.EntityNotFoundException;

public class RoadQualityNotFoundException extends EntityNotFoundException {
    public RoadQualityNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
