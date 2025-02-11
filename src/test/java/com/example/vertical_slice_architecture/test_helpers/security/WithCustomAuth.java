package com.example.vertical_slice_architecture.test_helpers.security;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = CustomAuthContextFactory.class)
public @interface WithCustomAuth {

    String id() default "26b6631e-487e-42bf-9912-22dfab79d67e";
}
