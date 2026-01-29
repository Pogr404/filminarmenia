package com.registration.ex;

public abstract class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
