package com.example.demo.util.exceptions;

public class IdNotFoundException extends RuntimeException{

    private static final String ERROR_MESSAGE="Record no exist in %S";

    public IdNotFoundException(String tableName){

        super(String.format(ERROR_MESSAGE,tableName));
    }

}
