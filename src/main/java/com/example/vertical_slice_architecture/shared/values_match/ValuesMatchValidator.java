package com.example.vertical_slice_architecture.shared.values_match;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Objects;

public class ValuesMatchValidator implements ConstraintValidator<ValuesMatch, Object> {

    private String value1;
    private String value2;

    @Override
    public void initialize(ValuesMatch constraintAnnotation) {
        this.value1 = constraintAnnotation.value1();
        this.value2 = constraintAnnotation.value2();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String property1 = (String) new BeanWrapperImpl(value)
                .getPropertyValue(this.value1);
        String property2 = (String) new BeanWrapperImpl(value)
                .getPropertyValue(this.value2);
        return Objects.equals(property1, property2);
    }
}
