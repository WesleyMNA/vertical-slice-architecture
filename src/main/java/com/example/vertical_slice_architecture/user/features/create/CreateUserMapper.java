package com.example.vertical_slice_architecture.user.features.create;

import com.example.vertical_slice_architecture.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class CreateUserMapper {

    private final PasswordEncoder encoder;

    User toUser(CreateRequest request) {
        return new User(
                request.name(),
                request.email(),
                encoder.encode(request.password())
        );
    }

    CreateResponse toResponse(User user) {
        return new CreateResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
