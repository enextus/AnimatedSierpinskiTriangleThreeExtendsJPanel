package org.serpinskitriangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.serpinskitriangleold.OptimizedSierpinskiTriangle;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.*;

class OptimizedSierpinskiTriangleTest {
    private OptimizedSierpinskiTriangle triangle;

    @BeforeEach
    void setUp() {
        triangle = new OptimizedSierpinskiTriangle();
    }

    @Test
    void testMidpoint() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        Point expectedMidpoint = new Point(5, 5);
        Point actualMidpoint = triangle.midpoint(p1, p2);
        assertEquals(expectedMidpoint, actualMidpoint, "Midpoint calculation is incorrect");
    }
}
