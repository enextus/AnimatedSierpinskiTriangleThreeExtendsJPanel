package org.serpinskitriangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages Sierpinski triangle operations such as division and initialization.
 */
public class TriangleManager {

    private final int maxDepth;
    private final int maxTriangles;

    public TriangleManager(int maxDepth, int maxTriangles) {
        this.maxDepth = maxDepth;
        this.maxTriangles = maxTriangles;
    }

    /**
     * Checks if further division is possible based on triangle count.
     */
    public boolean canDivide(int currentCount) {
        return currentCount + 3 <= maxTriangles;
    }

    /**
     * Divides a triangle into three smaller triangles.
     */
    public List<Point[]> divideTriangle(Point[] triangle) {
        List<Point[]> newTriangles = new ArrayList<>();
        Point p1 = midpoint(triangle[0], triangle[1]);
        Point p2 = midpoint(triangle[1], triangle[2]);
        Point p3 = midpoint(triangle[0], triangle[2]);

        newTriangles.add(new Point[]{triangle[0], p1, p3});
        newTriangles.add(new Point[]{p1, triangle[1], p2});
        newTriangles.add(new Point[]{p3, p2, triangle[2]});

        return newTriangles;
    }

    /**
     * Generates the initial triangle for the Sierpinski triangle.
     */
    public Point[] getInitialTriangle(int width, int height) {
        return new Point[]{
                new Point(width / 2, 0),
                new Point(0, height),
                new Point(width, height)
        };
    }

    /**
     * Calculates the midpoint between two given points.
     */
    public static Point midpoint(Point p1, Point p2) {
        int x = (p1.x + p2.x) / 2;
        int y = (p1.y + p2.y) / 2;
        return new Point(x, y);
    }

}
