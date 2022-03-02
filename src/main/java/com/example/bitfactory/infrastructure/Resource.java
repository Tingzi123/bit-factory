package com.example.bitfactory.infrastructure;

import com.example.bitfactory.infrastructure.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
public class Resource<T> {
    private final Date timestamp = new Date();
    private HttpStatus code = HttpStatus.OK;
    private ErrorCode errorCode = null;
    private String message = HttpStatus.OK.name();
    private final T data;

    private Resource(T data) {
        this.data = data;
    }

    public Resource(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.code = null;
        this.data = null;
    }

    public static <T> Resource<T> empty() {
        return new Resource<>(null);
    }

    public static <T> Resource<T> of(T data) {
        return new Resource<>(data);
    }

    public static <T> Resource<T> of( ErrorCode errorCode, String message) {
        return new Resource<>(errorCode, message);
    }
}
