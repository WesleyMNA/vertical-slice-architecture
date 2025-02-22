package com.example.vertical_slice_architecture.user.features.update;

import com.example.vertical_slice_architecture.user.shared.UserV1Controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@RequiredArgsConstructor
@UserV1Controller
final class UpdateUserController {

    private final UpdateUserService service;

    @PutMapping("{id}")
    ResponseEntity<Void> update(
            @PathVariable
            UUID id,
            @Valid
            @RequestBody
            UpdateRequest request
    ) {
        service.update(id, request);
        return ResponseEntity
                .noContent()
                .build();
    }
}
