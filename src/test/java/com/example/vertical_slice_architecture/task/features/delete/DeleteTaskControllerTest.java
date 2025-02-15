package com.example.vertical_slice_architecture.task.features.delete;

import com.example.vertical_slice_architecture.task.entities.Task;
import com.example.vertical_slice_architecture.task.infrastructure.TaskRepository;
import com.example.vertical_slice_architecture.task.shared.TaskConstants;
import com.example.vertical_slice_architecture.test_helpers.TestcontainersConfiguration;
import com.example.vertical_slice_architecture.test_helpers.controllers.DeleteControllerHelper;
import com.example.vertical_slice_architecture.test_helpers.security.CustomAuthConstants;
import com.example.vertical_slice_architecture.test_helpers.security.WithCustomAuth;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithCustomAuth
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(TestcontainersConfiguration.class)
class DeleteTaskControllerTest extends DeleteControllerHelper<UUID> {

    private final TaskRepository repository;

    @Autowired
    DeleteTaskControllerTest(MockMvc mvc, TaskRepository repository) {
        super(TaskConstants.BASE_V1_URI, mvc);
        this.repository = repository;
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldDeleteTask()
            throws Exception {
        var task = insertTask(CustomAuthConstants.AUTH_UUID_ID);

        performDelete(task.getId())
                .andExpect(status().isNoContent());

        Optional<Task> optional = repository.findById(task.getId());
        Assertions.assertTrue(optional.isEmpty());
    }

    @Test
    void shouldReturn_NotFoundWhenID_DoesNotExit()
            throws Exception {
        performDelete(UUID.randomUUID())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn_ForbiddenWhen_UserDoesNotHave_AccessToTask()
            throws Exception {
        var task = insertTask(UUID.randomUUID());

        performDelete(task.getId())
                .andExpect(status().isForbidden());
    }


    private Task insertTask(UUID userId) {
        var task = new Task(
                userId,
                faker.name().title(),
                faker.leagueOfLegends().quote(),
                LocalDate.now(),
                null
        );
        repository.save(task);
        return task;
    }
}