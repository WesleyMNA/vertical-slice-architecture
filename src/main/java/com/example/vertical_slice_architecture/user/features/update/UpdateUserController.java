package com.example.vertical_slice_architecture.user.features.update;

import com.example.vertical_slice_architecture.user.shared.UserConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(UserConstants.URI)
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
