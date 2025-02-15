package com.example.vertical_slice_architecture.task.features.delete;

import com.example.vertical_slice_architecture.task.shared.TaskConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(TaskConstants.BASE_V1_URI)
final class DeleteTaskController {

    private final DeleteTaskService service;

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(
            @PathVariable
            UUID id
    ) {
        service.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
