package com.example.vertical_slice_architecture.task.features.create;

import com.example.vertical_slice_architecture.task.shared.TaskConstants;
import com.example.vertical_slice_architecture.task.shared.dtos.TaskRequest;
import com.example.vertical_slice_architecture.task.shared.dtos.TaskResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(TaskConstants.BASE_V1_URI)
final class CreateTaskController {

    private final CreateTaskService service;

    @PostMapping
    ResponseEntity<TaskResponse> create(
            @Valid
            @RequestBody
            TaskRequest request,
            UriComponentsBuilder builder
    ) {
        TaskResponse response = service.create(request);
        URI uri = builder
                .pathSegment(TaskConstants.BASE_V1_URI, "/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(response);
    }
}
