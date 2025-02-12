package com.example.vertical_slice_architecture.user.features.update;

import com.example.vertical_slice_architecture.shared.rest.ConflictException;
import com.example.vertical_slice_architecture.shared.rest.NotFoundException;
import com.example.vertical_slice_architecture.user.domain.User;
import com.example.vertical_slice_architecture.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.vertical_slice_architecture.user.shared.UserConstants.EMAIL_ALREADY_EXISTS;
import static com.example.vertical_slice_architecture.user.shared.UserConstants.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
class UpdateUserService {

    private final UserRepository repository;

    void update(UUID id, UpdateRequest request) {
        User user = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        validate(id, request);
        user.setName(request.name());
        user.setEmail(request.email());
        repository.save(user);
    }

    private void validate(UUID id, UpdateRequest request) {
        validateEmail(id, request.email());
    }

    private void validateEmail(UUID id, String email) {
        boolean existsAnotherUserWithEmail = repository.existsByIdNotAndEmail(id, email);

        if  (existsAnotherUserWithEmail)
            throw new ConflictException(EMAIL_ALREADY_EXISTS);
    }
}
