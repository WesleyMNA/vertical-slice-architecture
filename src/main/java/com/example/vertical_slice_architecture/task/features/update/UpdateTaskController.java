package com.example.vertical_slice_architecture.task.features.update;

import com.example.vertical_slice_architecture.task.shared.TaskV1Controller;
import com.example.vertical_slice_architecture.task.shared.dtos.TaskRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@RequiredArgsConstructor
@TaskV1Controller
final class UpdateTaskController {

    private final UpdateTaskService service;

    @PutMapping("{id}")
    ResponseEntity<Void> update(
            @PathVariable
            UUID id,
            @Valid
            @RequestBody
            TaskRequest request
    ) {
        service.update(id, request);
        return ResponseEntity
                .noContent()
                .build();
    }
}
