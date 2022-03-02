package com.example.bitfactory.infrastructure.exception;

import com.example.bitfactory.infrastructure.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Resource<Void> handle(NotFoundException ex) {
        return Resource.of(ClientErrorCode.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Resource<Void> handle(BadRequestException ex) {
        return Resource.of(ClientErrorCode.BAD_REQUEST, ex.getMessage());
    }

    //Filetr
    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Resource<Void> handle(ForbiddenException ex) {
        return Resource.of(ClientErrorCode.FORBIDDEN, ex.getMessage());
    }

    //Filetr
    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Resource<Void> handle(AccessDeniedException ex) {
        return Resource.of(ClientErrorCode.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Resource<Void> handle(RuntimeException ex) {
        ex.printStackTrace();
        return Resource.of(ServerErrorCode.INTERNAL_SERVER_ERROR, "500");
    }
}
