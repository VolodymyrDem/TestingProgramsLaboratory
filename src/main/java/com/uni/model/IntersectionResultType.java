package com.uni.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum IntersectionResultType {
    COINCIDENT("Прямі співпадають") {
        @Override
        public String formatMessage(List<Point> points) {
            return getMessage();
        }
    },
    NO_INTERSECTION("Прямі не перетинаються") {
        @Override
        public String formatMessage(List<Point> points) {
            return getMessage();
        }
    },
    ONE_POINT("Єдина точка перетину прямих") {
        @Override
        public String formatMessage(List<Point> points) {
            if (points.isEmpty()) return getMessage();
            Point p = points.getFirst();
            return String.format("Єдина точка перетину прямих (x0, y0), x0 = %.6f, y0 = %.6f",
                    p.getX(), p.getY());
        }
    },
    TWO_POINTS("Дві точки перетину прямих") {
        @Override
        public String formatMessage(List<Point> points) {
            if (points.size() < 2) return getMessage();
            Point p1 = points.get(0);
            Point p2 = points.get(1);
            return String.format("Дві точки перетину прямих (x1, y1) = (%.6f, %.6f), (x2, y2) = (%.6f, %.6f)",
                    p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }
    },
    THREE_POINTS("Три точки перетину прямих") {
        @Override
        public String formatMessage(List<Point> points) {
            if (points.size() < 3) return getMessage();
            Point p1 = points.get(0);
            Point p2 = points.get(1);
            Point p3 = points.get(2);
            return String.format("Три точки перетину прямих (x1, y1), (x2, y2), (x3, y3), " +
                            "x1 = %.6f, y1 = %.6f, x2 = %.6f, y2 = %.6f, x3 = %.6f, y3 = %.6f",
                    p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
        }
    };

    private final String message;

    public abstract String formatMessage(List<Point> points);
}
