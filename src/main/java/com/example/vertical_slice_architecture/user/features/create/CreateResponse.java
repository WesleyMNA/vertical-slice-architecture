package com.example.vertical_slice_architecture.user.features.create;

import java.util.UUID;

record CreateResponse(
        UUID id,
        String name,
        String email
) {
}
