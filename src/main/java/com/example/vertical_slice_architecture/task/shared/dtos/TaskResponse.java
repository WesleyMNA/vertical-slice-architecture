package com.example.vertical_slice_architecture.task.shared.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record TaskResponse(
        UUID id,
        UUID userId,
        String title,
        String description,
        LocalDate day,
        LocalTime hour
) {
}
