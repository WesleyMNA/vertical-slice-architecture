package com.example.vertical_slice_architecture.task.infrastructure;

import com.example.vertical_slice_architecture.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
}
