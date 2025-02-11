package com.example.vertical_slice_architecture.shared.rest;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseRestException {

    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
