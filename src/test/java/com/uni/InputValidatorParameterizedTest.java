package com.uni;

import com.uni.exception.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorParameterizedTest {

    private final InputValidator validator = new InputValidator(-115, 115);

    @ParameterizedTest(name = "{0}: {2}")
    @DisplayName("Тести валідації діапазону")
    @CsvFileSource(resources = "/validation-tests.csv", numLinesToSkip = 1, delimiter = ',')
    void testValidation(
            String testName,
            double value,
            String paramName,
            boolean shouldPass,
            String description) {

        if (shouldPass) {
            assertDoesNotThrow(() -> validator.validateRange(value, paramName),
                    String.format("Тест '%s': Значення %f має бути валідним. %s",
                            testName, value, description));
            System.out.printf("✓ %s: Валідне значення %f для %s%n", testName, value, paramName);
        } else {
            assertThrows(ValidationException.class,
                    () -> validator.validateRange(value, paramName),
                    String.format("Тест '%s': Значення %f має бути невалідним. %s",
                            testName, value, description));
            System.out.printf("✓ %s: Невалідне значення %f коректно відхилено%n", testName, value);
        }
    }

    @ParameterizedTest(name = "{0}: {1}")
    @DisplayName("Тести валідації ненульових значень")
    @CsvFileSource(resources = "/nonzero-tests.csv", numLinesToSkip = 1, delimiter = ',')
    void testNonZeroValidation(
            String testName,
            double value,
            String paramName,
            boolean shouldPass,
            String description) {

        if (shouldPass) {
            assertDoesNotThrow(() -> {
                validator.validateRange(value, paramName);
                validator.validateNonZero(value, paramName);
            }, String.format("Тест '%s': Значення %f має бути ненульовим. %s",
                    testName, value, description));
            System.out.printf("✓ %s: Ненульове значення %f прийнято%n", testName, value);
        } else {
            assertThrows(ValidationException.class,
                    () -> validator.validateNonZero(value, paramName),
                    String.format("Тест '%s': Значення %f має бути відхилено як нульове. %s",
                            testName, value, description));
            System.out.printf("✓ %s: Нульове значення %f коректно відхилено%n", testName, value);
        }
    }
}
