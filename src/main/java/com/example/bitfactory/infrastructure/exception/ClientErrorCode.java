package com.example.bitfactory.infrastructure.exception;

import lombok.Getter;

@Getter
public enum ClientErrorCode implements ErrorCode {
    NOT_FOUND(404),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403);

    private final int status;

    ClientErrorCode(int status) {
        this.status = status;
    }
}
