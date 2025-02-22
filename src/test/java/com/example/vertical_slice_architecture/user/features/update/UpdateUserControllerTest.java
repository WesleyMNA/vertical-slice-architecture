package com.example.vertical_slice_architecture.user.features.update;

import com.example.vertical_slice_architecture.test_helpers.TestcontainersConfiguration;
import com.example.vertical_slice_architecture.test_helpers.controllers.PutControllerHelper;
import com.example.vertical_slice_architecture.test_helpers.security.WithCustomAuth;
import com.example.vertical_slice_architecture.user.entities.User;
import com.example.vertical_slice_architecture.user.infrastructure.UserRepository;
import com.example.vertical_slice_architecture.user.shared.UserConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithCustomAuth
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(TestcontainersConfiguration.class)
class UpdateUserControllerTest extends PutControllerHelper<UUID, UpdateRequest> {

    private final UserRepository repository;

    private User userInDb;

    @Autowired
    UpdateUserControllerTest(
            MockMvc mvc,
            ObjectMapper mapper,
            UserRepository repository
    ) {
        super(UserConstants.BASE_V1_URI, mvc, mapper);
        this.repository = repository;
    }

    @BeforeEach
    void setUp() {
        userInDb = new User(
                faker.name().name(),
                faker.internet().emailAddress(),
                faker.internet().password()
        );
        repository.save(userInDb);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldUpdateUserSuccessfully()
            throws Exception {
        var request = getUpdateRequest();
        performPut(userInDb.getId(), request)
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn_NotFoundFor_InvalidID()
            throws Exception {
        var request = getUpdateRequest();
        performPut(UUID.randomUUID(), request)
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn_ConflictFor_EmailAlreadyExists()
            throws Exception {
        var userToUpdate = new User(
                faker.name().name(),
                faker.internet().emailAddress(),
                faker.internet().password()
        );
        repository.save(userToUpdate);

        var requestWithUsersInDbEmail = new UpdateRequest(
                faker.name().name(),
                userInDb.getEmail()
        );
        performPut(userToUpdate.getId(), requestWithUsersInDbEmail)
                .andExpect(status().isConflict());
    }

    private @NotNull UpdateRequest getUpdateRequest() {
        return new UpdateRequest(
                faker.name().name(),
                faker.internet().emailAddress()
        );
    }
}