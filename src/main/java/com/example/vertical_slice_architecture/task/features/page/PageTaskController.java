package com.example.vertical_slice_architecture.task.features.page;

import com.example.vertical_slice_architecture.task.shared.TaskConstants;
import com.example.vertical_slice_architecture.task.shared.dtos.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(TaskConstants.BASE_V1_URI)
final class PageTaskController {

    private final PageTaskService service;
    @GetMapping
    ResponseEntity<Page<TaskResponse>> findAll(
            TaskSpecification spec,
            Pageable pageable
    ) {
        Page<TaskResponse> response = service.findAll(spec, pageable);
        return ResponseEntity.ok(response);
    }
}
