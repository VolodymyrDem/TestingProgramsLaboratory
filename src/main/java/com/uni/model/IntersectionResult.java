package com.uni.model;

import java.util.List;

public record IntersectionResult(IntersectionResultType type, List<Point> points) {
    public String getMessage() {
        return type.formatMessage(points);
    }
}
