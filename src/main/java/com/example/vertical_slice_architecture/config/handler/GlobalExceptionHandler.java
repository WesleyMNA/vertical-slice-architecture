package com.example.vertical_slice_architecture.config.handler;

import com.example.vertical_slice_architecture.shared.rest.BaseRestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Log4j2
@RequiredArgsConstructor
@ControllerAdvice
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
        ErrorResponse res = getResponseFromException(ex, uri);
        return ResponseEntity
                .status(res.status())
                .body(res);
    }

    private ErrorResponse getResponseFromException(Exception ex, String uri) {
        return switch (ex) {
            case AuthenticationException ignored ->
                    buildResponse(HttpStatus.UNAUTHORIZED, "invalid authentication", uri);
            case BaseRestException baseEx -> buildResponse(baseEx.getStatus(), baseEx.getMessage(), uri);
            case null, default -> {
                if (ex != null)
                    log.error("Exceção inesperada ocorreu na URI: {}. Exception class: {}, mensagem: {}",
                            uri, ex.getClass().getName(), ex.getMessage(), ex);

                yield buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error", uri);
            }
        };
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NotNull MethodArgumentNotValidException ex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatusCode status,
            @NotNull WebRequest request
    ) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
        });

        List<String> globalErrors = new ArrayList<>();
        ex.getBindingResult().getGlobalErrors().forEach(error -> globalErrors.add(error.getDefaultMessage()));

        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
        ErrorResponse res = buildResponse(HttpStatus.valueOf(status.value()), "validation error",
                uri, fieldErrors, globalErrors);
        return ResponseEntity
                .badRequest()
                .body(res);
    }

    private ErrorResponse buildResponse(HttpStatus status, String message, String uri) {
        return buildResponse(status, message, uri, null, null);
    }

    private ErrorResponse buildResponse(
            HttpStatus status,
            String message,
            String uri,
            Map<String, String> fieldErrors,
            List<String> globalErrors
    ) {
        return new ErrorResponse(
                status.getReasonPhrase(),
                message,
                uri,
                status.value(),
                LocalDateTime.now(),
                fieldErrors,
                globalErrors
        );
    }
}
