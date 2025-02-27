package com.example.vertical_slice_architecture.task.features.delete;

import com.example.vertical_slice_architecture.shared.rest.NotFoundException;
import com.example.vertical_slice_architecture.task.entities.Task;
import com.example.vertical_slice_architecture.task.infrastructure.TaskRepository;
import com.example.vertical_slice_architecture.task.shared.TaskConstants;
import com.example.vertical_slice_architecture.task.shared.TaskOwnershipValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeleteTaskService {

    private final TaskRepository repository;
    private final TaskOwnershipValidator validator;

    public void delete(UUID id) {
        Task task = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(TaskConstants.TASK_NOT_FOUND));
        validator.validate(task);
        repository.delete(task);
    }
}
