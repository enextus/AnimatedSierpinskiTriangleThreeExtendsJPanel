package org.serpinskitriangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AnimatedSierpinskiTriangleTest {
    private AnimatedSierpinskiTriangle triangle;

    @BeforeEach
    void setUp() {
        triangle = new AnimatedSierpinskiTriangle();
    }

    @Test
    void testMidpoint() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        Point expectedMidpoint = new Point(5, 5);
        Point actualMidpoint = triangle.midpoint(p1, p2);
        assertEquals(expectedMidpoint, actualMidpoint, "Midpoint calculation is incorrect");
    }

    @Test
    void testGetRandomPoints() {
        Point[] randomPoints = triangle.getRandomPoints();
        assertNotNull(randomPoints, "Random points array should not be null");
        assertEquals(3, randomPoints.length, "Random points array should have a length of 3");
    }

    @Test
    void testGenerateTriangles() {
        triangle.generateTriangles();
        ArrayList<Point[]> triangles = triangle.getTriangles();
        assertNotNull(triangles, "Triangles list should not be null");
        assertTrue(triangles.size() > 0, "Triangles list should not be empty");
    }
}
