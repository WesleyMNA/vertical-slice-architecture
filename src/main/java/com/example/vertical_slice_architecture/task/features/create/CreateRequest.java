package com.example.vertical_slice_architecture.task.features.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

record CreateRequest(
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
