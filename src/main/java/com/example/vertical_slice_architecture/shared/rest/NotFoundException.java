package com.example.vertical_slice_architecture.shared.rest;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseRestException {

    public NotFoundException() {
        super("Recurso não encontrado", HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
