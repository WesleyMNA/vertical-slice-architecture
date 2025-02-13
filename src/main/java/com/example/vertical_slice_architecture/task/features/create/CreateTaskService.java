package com.example.vertical_slice_architecture.task.features.create;

import com.example.vertical_slice_architecture.shared.auth.AuthHelper;
import com.example.vertical_slice_architecture.shared.auth.CurrentUser;
import com.example.vertical_slice_architecture.task.domain.Task;
import com.example.vertical_slice_architecture.task.infrastructure.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CreateTaskService {

    private final TaskRepository repository;
    private final CreateTaskMapper mapper;

    public CreateResponse create(CreateRequest request) {
        CurrentUser currentUser = AuthHelper.getCurrentUser();
        Task task = mapper.toEntity(request, currentUser.getId());
        repository.save(task);
        return mapper.toResponse(task);
    }
}
