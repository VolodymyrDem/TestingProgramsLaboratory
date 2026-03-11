package com.uni;

import com.uni.exception.ValidationException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputValidator {

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
        if (Constants.isZero(value)) {
            throw new ValidationException(
                    "Значення " + paramName + " не може дорівнювати 0 (|" + paramName + "| < " + Constants.EPSILON + ")",
                    "Введіть ненульове значення для параметра " + paramName
            );
        }
    }
}
