package org.serpinskitriangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.Point;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AnimatedSierpinskiTriangleThreeTest {
    private AnimatedSierpinskiTriangleThree triangle;

    @BeforeEach
    void setUp() {
        triangle = new AnimatedSierpinskiTriangleThree();
        JFrame frame = new JFrame("Test Frame");
    }

    @Test
    void testGetTriangles() {
        ArrayList<Point[]> triangles = triangle.getTriangles();
        assertNotNull(triangles);
        assertTrue(triangles.isEmpty());
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
        int width = 600;
        int height = 600;
        Point[] randomPoints = triangle.getRandomPoints(width, height);

        assertNotNull(randomPoints);
        assertEquals(3, randomPoints.length);

        for (Point point : randomPoints) {
            assertTrue(point.getX() >= 0 && point.getX() < width);
            assertTrue(point.getY() >= 0 && point.getY() < height);
        }
    }
}
