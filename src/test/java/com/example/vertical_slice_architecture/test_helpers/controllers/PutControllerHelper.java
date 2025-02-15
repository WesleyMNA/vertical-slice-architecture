package com.example.vertical_slice_architecture.test_helpers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

public class PutControllerHelper<I, R> {

    private final String uri;
    private final MockMvc mvc;
    private final ObjectMapper mapper;
    protected final Faker faker;

    protected PutControllerHelper(String uri, MockMvc mvc, ObjectMapper mapper) {
        this.uri = uri;
        this.mvc = mvc;
        this.mapper = mapper;
        this.faker = new Faker();
    }

    protected ResultActions performPut(I id, R request)
            throws Exception {
        String content = mapper.writeValueAsString(request);
        return mvc
                .perform(put(uri.concat("/{id}"), id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));
    }
}
