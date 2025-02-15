package com.example.vertical_slice_architecture.task.features.update;

import com.example.vertical_slice_architecture.task.entities.Task;
import com.example.vertical_slice_architecture.task.infrastructure.TaskRepository;
import com.example.vertical_slice_architecture.task.shared.TaskConstants;
import com.example.vertical_slice_architecture.task.shared.dtos.TaskRequest;
import com.example.vertical_slice_architecture.test_helpers.TestcontainersConfiguration;
import com.example.vertical_slice_architecture.test_helpers.controllers.PutControllerHelper;
import com.example.vertical_slice_architecture.test_helpers.security.CustomAuthConstants;
import com.example.vertical_slice_architecture.test_helpers.security.WithCustomAuth;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
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
class UpdateTaskControllerTest extends PutControllerHelper<UUID, TaskRequest> {

    private final TaskRepository repository;

    @Autowired
    UpdateTaskControllerTest(MockMvc mvc, ObjectMapper mapper, TaskRepository repository) {
        super(TaskConstants.BASE_V1_URI, mvc, mapper);
        this.repository = repository;
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldUpdateTask()
            throws Exception {
        var task = insertTask(CustomAuthConstants.AUTH_UUID_ID);

        var request = new TaskRequest(
                faker.name().title(),
                task.getDescription(),
                task.getDay(),
                task.getHour()
        );
        performPut(task.getId(), request)
                .andExpect(status().isNoContent());

        Optional<Task> optional = repository.findById(task.getId());
        Assertions.assertTrue(optional.isPresent());
        Task updatedTask = optional.get();
        Assertions.assertEquals(request.title(), updatedTask.getTitle());
    }

    @Test
    void shouldReturn_NotFoundWhenID_DoesNotExit()
            throws Exception {
        var request = generateRandomRequest();

        performPut(UUID.randomUUID(), request)
                .andExpect(status().isNotFound());
    }


    @Test
    void shouldReturn_ForbiddenWhen_UserDoesNotHave_AccessToTask()
            throws Exception {
        var task = insertTask(UUID.randomUUID());

        var request = generateRandomRequest();

        performPut(task.getId(), request)
                .andExpect(status().isForbidden());
    }

    private @NotNull TaskRequest generateRandomRequest() {
        return new TaskRequest(
                faker.name().title(),
                faker.leagueOfLegends().quote(),
                LocalDate.now(),
                null
        );
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