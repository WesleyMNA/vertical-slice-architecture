package com.example.vertical_slice_architecture.user.features.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

record LoginRequest(
        @Email
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
