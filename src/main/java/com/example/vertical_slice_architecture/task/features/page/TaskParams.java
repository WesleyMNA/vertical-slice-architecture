package com.example.vertical_slice_architecture.task.features.page;

import java.time.LocalDate;

public record TaskParams(
        String title,
        LocalDate day
) {
}
