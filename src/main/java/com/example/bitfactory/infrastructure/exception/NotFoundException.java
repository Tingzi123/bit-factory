package com.example.bitfactory.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AppException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, ClientErrorCode.NOT_FOUND, message);
    }

    public NotFoundException(ErrorCode codes, String message) {
        super(HttpStatus.NOT_FOUND, codes, message);
    }
}
