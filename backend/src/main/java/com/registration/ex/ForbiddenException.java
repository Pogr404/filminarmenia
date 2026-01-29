package com.registration.ex;

public class ForbiddenException extends RuntimeException{

    public ForbiddenException(String errorMessage){
        super(errorMessage);
    }
}
