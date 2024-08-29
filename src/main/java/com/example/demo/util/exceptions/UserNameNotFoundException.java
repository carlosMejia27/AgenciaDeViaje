package com.example.demo.util.exceptions;

public class UserNameNotFoundException extends RuntimeException {
    private static final String ERROR_MESSAGE="user no exist in %S";

    public UserNameNotFoundException(String msj){
        super(String.format(ERROR_MESSAGE,msj));
    }

}
