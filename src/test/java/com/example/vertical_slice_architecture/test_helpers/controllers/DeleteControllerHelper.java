package com.example.vertical_slice_architecture.test_helpers.controllers;

import com.github.javafaker.Faker;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

public class DeleteControllerHelper<I> {

    private final String uri;
    private final MockMvc mvc;
    protected final Faker faker;

    protected DeleteControllerHelper(String uri, MockMvc mvc) {
        this.uri = uri;
        this.mvc = mvc;
        this.faker = new Faker();
    }

    protected ResultActions performDelete(I id)
            throws Exception {
        return mvc
                .perform(delete(uri.concat("/{id}"), id)
                        .contentType(MediaType.APPLICATION_JSON));
    }
}
