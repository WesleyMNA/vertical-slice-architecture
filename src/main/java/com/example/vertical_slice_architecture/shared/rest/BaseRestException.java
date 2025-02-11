package com.example.vertical_slice_architecture.shared.rest;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseRestException extends RuntimeException {

    private final HttpStatus status;

    public BaseRestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
