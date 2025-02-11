package com.example.vertical_slice_architecture.test_helpers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class PostControllerHelper<T> {

    private final String uri;
    private final MockMvc mvc;
    private final ObjectMapper mapper;
    protected final Faker faker;

    protected PostControllerHelper(String uri, MockMvc mvc, ObjectMapper mapper) {
        this.uri = uri;
        this.mvc = mvc;
        this.mapper = mapper;
        this.faker = new Faker();
    }

    protected ResultActions performPost(T request)
            throws Exception {
        String content = mapper.writeValueAsString(request);
        return mvc
                .perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));
    }
}
