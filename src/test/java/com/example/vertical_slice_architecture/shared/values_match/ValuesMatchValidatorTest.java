package com.example.vertical_slice_architecture.shared.values_match;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValuesMatchValidatorTest {

    private ValuesMatchValidator validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        validator = new ValuesMatchValidator();
        context = Mockito.mock(ConstraintValidatorContext.class);

        var annotation = Mockito.mock(ValuesMatch.class);
        Mockito.when(annotation.value1()).thenReturn("value1");
        Mockito.when(annotation.value2()).thenReturn("value2");

        validator.initialize(annotation);
    }

    @Test
    void testingMatchingValues() {
        var testDto = new TestDto("12345", "12345");
        boolean isValid = validator.isValid(testDto, context);
        assertTrue(isValid);
    }

    @Test
    void testingDifferentValues() {
        var testDto = new TestDto("12345", "65459");
        boolean isValid = validator.isValid(testDto, context);
        assertFalse(isValid);
    }

    @Test
    void testingOneFieldWithNull() {
        var testDto = new TestDto("12345", null);
        var isValid = validator.isValid(testDto, context);
        assertFalse(isValid);
    }

    @Test
    void testingBothFieldsWithNull() {
        var testDto = new TestDto(null, null);
        var isValid = validator.isValid(testDto, context);
        assertTrue(isValid);
    }

    private record TestDto(String value1, String value2) {
    }
}
