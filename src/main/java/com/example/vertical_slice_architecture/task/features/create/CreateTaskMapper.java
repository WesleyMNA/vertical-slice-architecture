package com.example.vertical_slice_architecture.task.features.create;

import com.example.vertical_slice_architecture.task.domain.Task;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class CreateTaskMapper {

    public Task toEntity(CreateRequest request, UUID id) {
        return new Task(
                id,
                request.title(),
                request.description(),
                request.day(),
                request.hour()
        );
    }

    public CreateResponse toResponse(Task task) {
        return new CreateResponse(
                task.getId(),
                task.getUserId(),
                task.getTitle(),
                task.getDescription(),
                task.getDay(),
                task.getHour()
        );
    }
}
