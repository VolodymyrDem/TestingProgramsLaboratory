package com.uni;

import com.uni.exception.ValidationException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputValidator {
    private static final double EPSILON = 1e-8;

    private final int minValue;
    private final int maxValue;

    public InputValidator(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public void validateRange(double value, String paramName) throws ValidationException {
        if (value < minValue || value > maxValue) {
            throw new ValidationException(
                    "Значення " + paramName + " (" + value + ") поза допустимим діапазоном [" + minValue + "; " + maxValue + "]",
                    "Введіть значення в діапазоні [" + minValue + "; " + maxValue + "]"
            );
        }
    }

    public void validateNonZero(double value, String paramName) throws ValidationException {
        if (isZero(value)) {
            throw new ValidationException(
                    "Значення " + paramName + " не може дорівнювати 0 (|" + paramName + "| < " + EPSILON + ")",
                    "Введіть ненульове значення для параметра " + paramName
            );
        }
    }

    public static boolean isZero(double value) {
        return Math.abs(value) < EPSILON;
    }
}
