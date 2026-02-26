package com.uni;

/**
 * Клас для зберігання загальних констант і утилітних методів проекту.
 */
public final class Constants {
    public static final double EPSILON = 1e-8;

    public static boolean isZero(double value) {
        return Math.abs(value) < EPSILON;
    }

    private Constants() {
        throw new AssertionError("Не можна створити екземпляр класу Constants");
    }
}
