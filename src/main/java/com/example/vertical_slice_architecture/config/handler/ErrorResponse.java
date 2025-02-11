package com.example.vertical_slice_architecture.config.handler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
record ErrorResponse(
        String error,
        String message,
        String path,
        int status,
        LocalDateTime timestamp,
        Map<String, String> fieldErrors,
        List<String> globalErrors
) {
}
