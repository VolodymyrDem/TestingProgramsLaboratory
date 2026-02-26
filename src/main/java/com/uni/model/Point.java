package com.uni.model;

import com.uni.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Point {

    private final double x;
    private final double y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Constants.isZero(this.x - point.x) && Constants.isZero(this.y - point.y);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(Math.round(x / Constants.EPSILON), Math.round(y / Constants.EPSILON));
    }
}
