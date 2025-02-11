package com.example.vertical_slice_architecture.shared.rest;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseRestException {

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
