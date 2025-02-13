package com.example.vertical_slice_architecture.task.features.create;

import com.example.vertical_slice_architecture.task.domain.Task;
import com.example.vertical_slice_architecture.task.infrastructure.TaskRepository;
import com.example.vertical_slice_architecture.task.shared.TaskConstants;
import com.example.vertical_slice_architecture.test_helpers.TestcontainersConfiguration;
import com.example.vertical_slice_architecture.test_helpers.controllers.PostControllerHelper;
import com.example.vertical_slice_architecture.test_helpers.security.WithCustomAuth;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithCustomAuth
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(TestcontainersConfiguration.class)
class CreateTaskControllerTest extends PostControllerHelper<CreateRequest> {

    private final TaskRepository repository;


    @Autowired
    CreateTaskControllerTest(MockMvc mvc, ObjectMapper mapper, TaskRepository repository) {
        super(TaskConstants.BASE_V1_URI, mvc, mapper);
        this.repository = repository;
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldCreateTask()
            throws Exception {
        var request = new CreateRequest(
                faker.name().title(),
                faker.leagueOfLegends().quote(),
                LocalDate.now(),
                null
        );

        performPost(request)
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

        List<Task> tasks = repository.findAll();
        Assertions.assertEquals(1, tasks.size());
    }
}