package com.uni;

import com.uni.model.IntersectionResult;
import com.uni.model.IntersectionResultType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class LineIntersectionCalculatorParameterizedTest {

    private final LineIntersectionCalculator calculator = new LineIntersectionCalculator();

    @ParameterizedTest(name = "{0}: {12}")
    @DisplayName("Параметризовані тести з CSV файлу")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1, delimiter = ',')
    void testFromCsvFile(
            String testName,
            double x01, double y01, double l1, double m1,
            double x02, double y02, double l2, double m2,
            double k, double b,
            String expectedType,
            int expectedPointsCount,
            String description) {

        IntersectionResult result =
                calculator.calculate(x01, y01, l1, m1, x02, y02, l2, m2, k, b);

        assertNotNull(result, "Результат не повинен бути null для тесту: " + testName);
        assertNotNull(result.type(), "Тип результату не повинен бути null для тесту: " + testName);
        assertNotNull(result.points(), "Список точок не повинен бути null для тесту: " + testName);

        if (!"ANY".equals(expectedType)) {
            IntersectionResultType expected =
                    IntersectionResultType.valueOf(expectedType);
            assertEquals(expected, result.type(),
                    String.format("Тест '%s': Очікувався тип %s, але отримано %s. Опис: %s",
                            testName, expectedType, result.type(), description));
        }

        if (expectedPointsCount > 0 || !"ANY".equals(expectedType)) {
            assertEquals(expectedPointsCount, result.points().size(),
                    String.format("Тест '%s': Очікувалась %d точка(ок), але отримано %d. Опис: %s",
                            testName, expectedPointsCount, result.points().size(), description));
        }

        System.out.printf("✓ %s: %s - %s (точок: %d)%n",
                testName, result.type(), description, result.points().size());
    }

    @ParameterizedTest(name = "Граничний тест {0}")
    @DisplayName("Тести граничних значень")
    @CsvFileSource(resources = "/boundary-tests.csv", numLinesToSkip = 1, delimiter = ',')
    void testBoundaryValues(
            String testName,
            double x01, double y01, double l1, double m1,
            double x02, double y02, double l2, double m2,
            double k, double b,
            String description) {

        IntersectionResult result =
                calculator.calculate(x01, y01, l1, m1, x02, y02, l2, m2, k, b);

        assertNotNull(result, "Результат не повинен бути null для граничного тесту: " + testName);
        assertNotNull(result.type(), "Тип результату не повинен бути null");
        assertNotNull(result.points(), "Список точок не повинен бути null");

        System.out.printf("✓ Граничний тест %s: %s - %s%n", testName, result.type(), description);
    }
}