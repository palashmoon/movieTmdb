package com.stackroute.Exeptions;

public class MovieNotFoundExection extends Exception{
    private String message;

    public MovieNotFoundExection(){}

    public MovieNotFoundExection(String message){
        super(message);
        this.message = message;
    }
}
