package com.crudexample.online.exceptions;

public class IncorrectIdException extends RuntimeException{
    public IncorrectIdException(String message) {
        super(message);
    }
}
