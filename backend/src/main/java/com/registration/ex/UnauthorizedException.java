package com.registration.ex;

public abstract class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(String errorMessage) {
        super(errorMessage);
    }
}
