package com.example.vertical_slice_architecture.task.features.page;

import com.example.vertical_slice_architecture.task.infrastructure.TaskRepository;
import com.example.vertical_slice_architecture.task.shared.TaskMapper;
import com.example.vertical_slice_architecture.task.shared.dtos.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class PageTaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    Page<TaskResponse> findAll(TaskSpecification spec, Pageable pageable) {
        return repository
                .findAll(spec, pageable)
                .map(mapper::toResponse);
    }
}
