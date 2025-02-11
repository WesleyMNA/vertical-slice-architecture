package com.example.vertical_slice_architecture.user.features.create;

import com.example.vertical_slice_architecture.shared.rest.ConflictException;
import com.example.vertical_slice_architecture.user.domain.User;
import com.example.vertical_slice_architecture.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CreateUserService {

    private final UserRepository repository;
    private final CreateUserMapper mapper;

    CreateResponse create(CreateRequest request) {
        validate(request);
        User user = mapper.toUser(request);
        repository.save(user);
        return mapper.toResponse(user);
    }

    void validate(CreateRequest request) {
        validateEmailExistence(request.email());
    }

    private void validateEmailExistence(String email) {
        boolean emailExists = repository.existsByEmail(email);

        if (emailExists)
            throw new ConflictException("email already exists");
    }
}
