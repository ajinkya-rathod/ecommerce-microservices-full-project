package com.userservice.exception;

public class ResourceAlreadyExistException extends RuntimeException{

    public ResourceAlreadyExistException(String msg){
         super(msg);
    }
}
