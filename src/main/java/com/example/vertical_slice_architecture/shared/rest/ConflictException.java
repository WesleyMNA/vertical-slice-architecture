package com.example.vertical_slice_architecture.shared.rest;

import org.springframework.http.HttpStatus;

public class ConflictException extends BaseRestException {

    public ConflictException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
