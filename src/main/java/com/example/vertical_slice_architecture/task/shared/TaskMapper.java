package com.example.vertical_slice_architecture.task.shared;

import com.example.vertical_slice_architecture.task.entities.Task;
import com.example.vertical_slice_architecture.task.shared.dtos.TaskRequest;
import com.example.vertical_slice_architecture.task.shared.dtos.TaskResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TaskMapper {

    public Task toEntity(TaskRequest request, UUID id) {
        return new Task(
                id,
                request.title(),
                request.description(),
                request.day(),
                request.hour()
        );
    }

    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getUserId(),
                task.getTitle(),
                task.getDescription(),
                task.getDay(),
                task.getHour()
        );
    }
}
