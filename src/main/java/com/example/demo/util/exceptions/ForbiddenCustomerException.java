package com.example.demo.util.exceptions;

public class ForbiddenCustomerException extends RuntimeException {

    public ForbiddenCustomerException(){
        super("This Customer is blocked");
    }
}
