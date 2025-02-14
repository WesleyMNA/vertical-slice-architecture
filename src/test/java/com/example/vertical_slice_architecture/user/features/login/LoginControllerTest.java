package com.example.vertical_slice_architecture.user.features.login;

import com.example.vertical_slice_architecture.test_helpers.TestcontainersConfiguration;
import com.example.vertical_slice_architecture.test_helpers.controllers.PostControllerHelper;
import com.example.vertical_slice_architecture.user.entities.User;
import com.example.vertical_slice_architecture.user.infrastructure.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(TestcontainersConfiguration.class)
class LoginControllerTest extends PostControllerHelper<LoginRequest> {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    private final String validEmail = "test.user@email.com";
    private final String validPassword = "12345";

    @Autowired
    LoginControllerTest(
            MockMvc mvc,
            UserRepository repository,
            ObjectMapper objectMapper,
            PasswordEncoder encoder
    ) {
        super("/v1/login", mvc, objectMapper);
        this.repository = repository;
        this.encoder = encoder;
    }

    @BeforeEach
    void setUp() {
        var user = new User(
                "Test User",
                validEmail,
                encoder.encode(validPassword)
        );
        repository.save(user);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldLoginSuccessfully()
            throws Exception {
        var request = new LoginRequest(validEmail, validPassword);
        performPost(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("token").exists())
                .andExpect(jsonPath("type").exists());
    }

    @Test
    void shouldReturn_UnauthorizedFor_InvalidCredentials()
            throws Exception {
        var request = new LoginRequest("invalid@email.com", "abcde");
        performPost(request)
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn_BadRequestFor_BodyMalformed()
            throws Exception {
        var request = new LoginRequest("invalid", null);
        performPost(request)
                .andExpect(status().isBadRequest());
    }
}