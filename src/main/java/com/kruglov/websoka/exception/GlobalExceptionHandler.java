package com.kruglov.websoka.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.kruglov.websoka.dto.UserResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // @ResponseBody
    // public LoginResponse handleValidation(AuthenticationCredentialsNotFoundException exc) {
    //     System.out.println(exc.getMessage());
    //     return new LoginResponse("lolka");
    // }

    @ExceptionHandler(ChatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleValidation(ChatException exc) {
        System.out.println(exc.getMessage());
        return new ErrorResponse(exc.getMessage());
    }



}