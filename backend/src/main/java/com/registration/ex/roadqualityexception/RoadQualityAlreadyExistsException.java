package com.registration.ex.roadqualityexception;

import com.registration.ex.ResourceAlreadyExistException;

public class RoadQualityAlreadyExistsException extends ResourceAlreadyExistException {
    public RoadQualityAlreadyExistsException(String message) {
        super(message);
    }
}
