package com.uni;

import com.uni.model.IntersectionResult;
import com.uni.model.IntersectionResultType;
import com.uni.model.Point;

import java.util.ArrayList;
import java.util.List;

public class LineIntersectionCalculator {

    public IntersectionResult calculate(
            double x01, double y01, double l1, double m1,
            double x02, double y02, double l2, double m2,
            double k, double b) {

        double a1 = m1;
        double b1 = -l1;
        double c1 = l1 * y01 - m1 * x01;

        double a2 = m2;
        double b2 = -l2;
        double c2 = l2 * y02 - m2 * x02;

        double a3 = k;
        double b3 = -1.0;
        double c3 = b;

        return analyzeGeneralCase(a1, b1, c1, a2, b2, c2, a3, b3, c3);
    }

    private Point findIntersection(double a1, double b1, double c1,
                                   double a2, double b2, double c2) {
        double det = a1 * b2 - a2 * b1;

        if (Constants.isZero(det)) {
            return null;
        }

        double x = -(c1 * b2 - c2 * b1) / det;
        double y = -(a1 * c2 - a2 * c1) / det;

        return new Point(x, y);
    }

    private boolean areCoincident(double a1, double b1, double c1,
                                  double a2, double b2, double c2) {
        return Constants.isZero(a1 * b2 - a2 * b1) &&
                Constants.isZero(a1 * c2 - a2 * c1) &&
                Constants.isZero(b1 * c2 - b2 * c1);
    }

    private IntersectionResult analyzeGeneralCase(double a1, double b1, double c1,
                                                  double a2, double b2, double c2,
                                                  double a3, double b3, double c3) {
        if (areCoincident(a1, b1, c1, a2, b2, c2) && areCoincident(a2, b2, c2, a3, b3, c3)) {
            return new IntersectionResult(IntersectionResultType.COINCIDENT, new ArrayList<>());
        }

        Point p12 = findIntersection(a1, b1, c1, a2, b2, c2);
        Point p13 = findIntersection(a1, b1, c1, a3, b3, c3);
        Point p23 = findIntersection(a2, b2, c2, a3, b3, c3);

        List<Point> uniquePoints = new ArrayList<>();
        if (p12 != null) uniquePoints.add(p12);
        if (p13 != null && !uniquePoints.contains(p13)) uniquePoints.add(p13);
        if (p23 != null && !uniquePoints.contains(p23)) uniquePoints.add(p23);

        if (uniquePoints.isEmpty()) return new IntersectionResult(IntersectionResultType.NO_INTERSECTION, new ArrayList<>());
        if (uniquePoints.size() == 1) return new IntersectionResult(IntersectionResultType.ONE_POINT, uniquePoints);
        if (uniquePoints.size() == 2) return new IntersectionResult(IntersectionResultType.TWO_POINTS, uniquePoints);
        return new IntersectionResult(IntersectionResultType.THREE_POINTS, uniquePoints);
    }
}
