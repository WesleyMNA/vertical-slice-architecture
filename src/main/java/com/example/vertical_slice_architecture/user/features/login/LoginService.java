package com.example.vertical_slice_architecture.user.features.login;

import com.example.vertical_slice_architecture.shared.rest.UnauthorizedException;
import com.example.vertical_slice_architecture.user.entities.User;
import com.example.vertical_slice_architecture.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class LoginService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final TokenGenerator tokenGenerator;

    private static final String INVALID_CREDENTIALS_MESSAGE = "invalid credentials";

    LoginResponse login(LoginRequest request) {
        User user = repository
                .findByEmail(request.email())
                .orElseThrow(() -> new UnauthorizedException(INVALID_CREDENTIALS_MESSAGE));
        validatePassword(request.password(), user.getPassword());
        String token = tokenGenerator.generate(user.getId());
        return new LoginResponse(token, "bearer");
    }

    private void validatePassword(String rawPassword, String encodedPassword) {
        if (!encoder.matches(rawPassword, encodedPassword))
            throw new UnauthorizedException(INVALID_CREDENTIALS_MESSAGE);
    }
}
