package com.example.vertical_slice_architecture.task.features.create;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

record CreateResponse(
        UUID id,
        UUID userId,
        String title,
        String description,
        LocalDate day,
        LocalTime hour
) {
}
