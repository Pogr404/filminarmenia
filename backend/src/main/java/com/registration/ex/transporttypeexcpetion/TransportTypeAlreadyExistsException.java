package com.registration.ex.transporttypeexcpetion;

import com.registration.ex.ResourceAlreadyExistException;

public class TransportTypeAlreadyExistsException extends ResourceAlreadyExistException {
    public TransportTypeAlreadyExistsException(String message) {
        super(message);
    }
}
