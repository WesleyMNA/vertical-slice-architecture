package com.example.vertical_slice_architecture.task.features.delete;

import com.example.vertical_slice_architecture.shared.auth.AuthHelper;
import com.example.vertical_slice_architecture.shared.auth.CurrentUser;
import com.example.vertical_slice_architecture.shared.rest.ForbiddenException;
import com.example.vertical_slice_architecture.shared.rest.NotFoundException;
import com.example.vertical_slice_architecture.task.entities.Task;
import com.example.vertical_slice_architecture.task.infrastructure.TaskRepository;
import com.example.vertical_slice_architecture.task.shared.TaskConstants;
import com.example.vertical_slice_architecture.user.shared.UserConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeleteTaskService {

    private final TaskRepository repository;

    public void delete(UUID id) {
        Task task = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(TaskConstants.TASK_NOT_FOUND));
        validateUserPermission(task);
        repository.delete(task);
    }

    private void validateUserPermission(Task task) {
        CurrentUser currentUser = AuthHelper.getCurrentUser();

        if (!task.getUserId().equals(currentUser.getId()))
            throw new ForbiddenException(UserConstants.USER_DOES_NOT_HAVE_PERMISSION);
    }
}
