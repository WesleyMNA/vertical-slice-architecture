package com.example.vertical_slice_architecture.task.features.update;

import com.example.vertical_slice_architecture.shared.rest.NotFoundException;
import com.example.vertical_slice_architecture.task.entities.Task;
import com.example.vertical_slice_architecture.task.infrastructure.TaskRepository;
import com.example.vertical_slice_architecture.task.shared.TaskConstants;
import com.example.vertical_slice_architecture.task.shared.TaskOwnershipValidator;
import com.example.vertical_slice_architecture.task.shared.dtos.TaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
class UpdateTaskService {

    private final TaskRepository repository;
    private final TaskOwnershipValidator validator;

    void update(UUID id, TaskRequest request) {
        Task task = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(TaskConstants.TASK_NOT_FOUND));
        validator.validate(task);
        BeanUtils.copyProperties(request, task);
        repository.save(task);
    }
}
