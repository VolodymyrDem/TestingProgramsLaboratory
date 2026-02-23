package com.uni.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Point {
    private static final double EPSILON = 1e-8;

    private final double x;
    private final double y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return isZero(this.x - point.x) && isZero(this.y - point.y);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(Math.round(x / EPSILON), Math.round(y / EPSILON));
    }

    private static boolean isZero(double value) {
        return Math.abs(value) < EPSILON;

    }
}
