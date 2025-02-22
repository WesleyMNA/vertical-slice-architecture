package com.example.vertical_slice_architecture.task.features.page;

import com.example.vertical_slice_architecture.task.shared.TaskV1Controller;
import com.example.vertical_slice_architecture.task.shared.dtos.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@TaskV1Controller
final class PageTaskController {

    private final PageTaskService service;

    @GetMapping
    ResponseEntity<Page<TaskResponse>> findAll(
            TaskParams params,
            Pageable pageable
    ) {
        Page<TaskResponse> response = service.findAll(params, pageable);
        return ResponseEntity.ok(response);
    }
}
