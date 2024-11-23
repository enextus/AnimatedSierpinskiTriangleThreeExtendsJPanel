package org.serpinskitriangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TriangleManager {

    private final int maxTriangles;
    private final int maxDepth;

    public TriangleManager(int maxDepth, int maxTriangles) {
        this.maxDepth = maxDepth;
        this.maxTriangles = maxTriangles;
    }

    public List<Point[]> divideTriangle(Point[] points) {
        if (points == null || points.length != 3) {
            throw new IllegalArgumentException("Треугольник должен содержать 3 вершины.");
        }
        if (points[0] == null || points[1] == null || points[2] == null) {
            throw new NullPointerException("Вершины треугольника не должны быть null.");
        }

        Point p1 = midpoint(points[0], points[1]);
        Point p2 = midpoint(points[1], points[2]);
        Point p3 = midpoint(points[0], points[2]);

        List<Point[]> result = new ArrayList<>();
        result.add(new Point[]{points[0], p1, p3});
        result.add(new Point[]{p1, points[1], p2});
        result.add(new Point[]{p3, p2, points[2]});

        return result;
    }

    public boolean canDivide(int currentTriangles) {
        if (currentTriangles < 0) {
            throw new IllegalArgumentException("Количество треугольников не может быть отрицательным");
        }
        return currentTriangles + 3 <= maxTriangles;
    }

    public Point[] getInitialTriangle(int width, int height) {
        return new Point[]{
                new Point(width / 2, 0),
                new Point(0, height),
                new Point(width, height)
        };
    }

    private Point midpoint(Point p1, Point p2) {
        return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

}
