package com.example.bitfactory.infrastructure.exception;

import lombok.Getter;

@Getter
public enum ServerErrorCode implements ErrorCode {
    INTERNAL_SERVER_ERROR(500);

    private final int status;

    ServerErrorCode(int status) {
        this.status = status;
    }
}
