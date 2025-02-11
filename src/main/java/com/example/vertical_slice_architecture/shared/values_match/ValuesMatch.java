package com.example.vertical_slice_architecture.shared.values_match;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValuesMatchValidator.class)
@Target({ElementType.TYPE})
public @interface ValuesMatch {

    String message() default "values must match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String value1();
    String value2();
}
