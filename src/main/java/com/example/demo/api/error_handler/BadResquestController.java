package com.example.demo.api.error_handler;

import com.example.demo.api.models.response.BaseErrorResponse;
import com.example.demo.api.models.response.ErrorRespose;
import com.example.demo.api.models.response.Errorsresponse;
import com.example.demo.util.exceptions.IdNotFoundException;
import com.example.demo.util.exceptions.UserNameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Array;
import java.util.ArrayList;


//@ResponseStatus(HttpStatus.BAD_REQUEST)
@RestControllerAdvice
public class BadResquestController {

    @ExceptionHandler({IdNotFoundException.class, UserNameNotFoundException.class})
    public BaseErrorResponse handleIdNotFound(RuntimeException exception){
        return ErrorRespose.builder()
                .mesaje(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handleIdNotFound(MethodArgumentNotValidException exception){
        var errors=new ArrayList<String>();
        exception.getAllErrors()
                .forEach(error ->errors.add(error.getDefaultMessage()));

        return Errorsresponse.builder()
                .ListaError(errors)
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }
}
