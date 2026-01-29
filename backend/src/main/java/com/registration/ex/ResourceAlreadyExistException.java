package com.registration.ex;

public abstract class ResourceAlreadyExistException extends RuntimeException{

    public ResourceAlreadyExistException(String errorMessage){
        super(errorMessage);
    }
}
