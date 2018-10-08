package com.stackroute.Exeptions;

public class MovieAlreadyExistException extends Exception {
    private String message;

    public MovieAlreadyExistException(){}

    public MovieAlreadyExistException(String message){
        super(message);
        this.message = message;
    }
}
