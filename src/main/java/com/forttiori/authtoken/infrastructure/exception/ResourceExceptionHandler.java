package com.forttiori.authtoken.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleResponseStatus(ResponseStatusException e) {
        return ExceptionResponse.builder()
                .message("There is a problem with your request. Check it and try again.")
                .build();
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ExceptionResponse handleResponseStatus(AuthorizationDeniedException e) {
        return ExceptionResponse.builder()
                .message("Not allowed to access this resource.")
                .build();
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse exception(Exception e) {
        return ExceptionResponse.builder()
                .message("There was a problem with our application.")
                .build();
    }

}
