package com.example.demo.api.error_handler;

import com.example.demo.api.models.response.BaseErrorResponse;
import com.example.demo.api.models.response.ErrorRespose;
import com.example.demo.util.exceptions.ForbiddenCustomerException;
import com.example.demo.util.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForddibenCustomerHandler {

    @ExceptionHandler(ForbiddenCustomerException.class)
    public BaseErrorResponse handleIdNotFound(ForbiddenCustomerException exception){
        return ErrorRespose.builder()
                .mesaje(exception.getMessage())
                .status(HttpStatus.FORBIDDEN.name())
                .code(HttpStatus.FORBIDDEN.value())
                .build();
    }
}
