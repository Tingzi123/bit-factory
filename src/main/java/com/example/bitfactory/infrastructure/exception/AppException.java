package com.example.bitfactory.infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class AppException extends RuntimeException {
    private final ErrorCode code;
    private final HttpStatus status;

    protected AppException(ErrorCode code, String message) {
        super(message);
        this.code = code;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    protected AppException(HttpStatus status, ErrorCode codes, String message) {
        super(message);
        this.status = status;
        this.code = codes;
    }
}
