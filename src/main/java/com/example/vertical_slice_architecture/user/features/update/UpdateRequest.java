package com.example.vertical_slice_architecture.user.features.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateRequest(
        @Size(min = 5, max = 255)
        @NotBlank
        String name,
        @Size(max = 128)
        @Email
        @NotBlank
        String email
) {
}
