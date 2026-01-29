package com.registration.ex.gasstationexception;
import com.registration.ex.ResourceAlreadyExistException;

public class GasStationAlreadyExistsException extends ResourceAlreadyExistException {
    public GasStationAlreadyExistsException(String message) {
        super(message);
    }
}
