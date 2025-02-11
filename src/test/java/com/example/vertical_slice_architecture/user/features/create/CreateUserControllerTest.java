package com.example.vertical_slice_architecture.user.features.create;

import com.example.vertical_slice_architecture.test_helpers.TestcontainersConfiguration;
import com.example.vertical_slice_architecture.test_helpers.controllers.PostControllerHelper;
import com.example.vertical_slice_architecture.user.domain.User;
import com.example.vertical_slice_architecture.user.infrastructure.UserRepository;
import com.example.vertical_slice_architecture.user.resources.UserConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(TestcontainersConfiguration.class)
class CreateUserControllerTest extends PostControllerHelper<CreateRequest> {

    private final UserRepository repository;

    private String usedEmail;

    @Autowired
    CreateUserControllerTest(
            MockMvc mvc,
            ObjectMapper mapper,
            UserRepository repository
    ) {
        super(UserConstants.URI, mvc, mapper);
        this.repository = repository;
    }

    @BeforeEach
    void setUp() {
        usedEmail = faker.internet().emailAddress();
        var user = new User(
                faker.name().name(),
                usedEmail,
                faker.internet().password()
        );
        repository.save(user);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldCreateUserSuccessfully()
            throws Exception {
        String password = faker.internet().password();
        var request = new CreateRequest(
                faker.name().name(),
                faker.internet().emailAddress(),
                password,
                password
        );
        performPost(request)
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
        Optional<User> createdUser = repository.findByEmail(request.email());
        Assertions.assertTrue(createdUser.isPresent());
    }

    @Test
    void shouldReturn_ConflictFor_EmailAlreadyExists()
            throws Exception {
        String password = faker.internet().password();
        var request = new CreateRequest(
                faker.name().name(),
                usedEmail,
                password,
                password
        );
        performPost(request)
                .andExpect(status().isConflict());
    }
}