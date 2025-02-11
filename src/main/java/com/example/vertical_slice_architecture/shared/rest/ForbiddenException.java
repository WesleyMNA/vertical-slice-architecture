package com.example.vertical_slice_architecture.shared.rest;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends BaseRestException {

    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
