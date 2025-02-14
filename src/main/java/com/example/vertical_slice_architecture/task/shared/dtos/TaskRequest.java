package com.example.vertical_slice_architecture.task.shared.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public record TaskRequest(
        @NotBlank
        @Size(max = 100)
        String title,
        @NotBlank
        String description,
        @NotNull
        LocalDate day,
        LocalTime hour
) {
}
