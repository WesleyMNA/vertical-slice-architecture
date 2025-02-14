package com.example.vertical_slice_architecture.task.features.page;

import com.example.vertical_slice_architecture.shared.auth.AuthHelper;
import com.example.vertical_slice_architecture.task.infrastructure.TaskRepository;
import com.example.vertical_slice_architecture.task.shared.TaskMapper;
import com.example.vertical_slice_architecture.task.shared.dtos.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
class PageTaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    Page<TaskResponse> findAll(TaskParams params, Pageable pageable) {
        UUID userId = AuthHelper.getCurrentUser().getId();
        var spec = new TaskSpecification(userId, params.title(), params.day());
        return repository
                .findAll(spec, pageable)
                .map(mapper::toResponse);
    }
}
