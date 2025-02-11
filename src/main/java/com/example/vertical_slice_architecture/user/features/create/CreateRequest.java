package com.example.vertical_slice_architecture.user.features.create;

import com.example.vertical_slice_architecture.shared.values_match.ValuesMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@ValuesMatch(value1 = "password", value2 = "confirmPassword")
record CreateRequest(
        @Size(min = 5, max = 255)
        @NotBlank
        String name,
        @Size(max = 128)
        @Email
        @NotBlank
        String email,
        @Size(min = 8)
        @NotBlank
        String password,
        @Size(min = 8)
        @NotBlank
        String confirmPassword
) {
}
