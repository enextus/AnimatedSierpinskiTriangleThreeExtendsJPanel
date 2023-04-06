package org.serpinskitriangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AnimatedSierpinskiTriangleTwoTest {
    private AnimatedSierpinskiTriangleTwo triangle;

    @BeforeEach
    void setUp() {
        triangle = new AnimatedSierpinskiTriangleTwo();
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
        Point[] randomPoints = triangle.getRandomPoints(100,100);
        assertNotNull(randomPoints, "Random points array should not be null");
        assertEquals(3, randomPoints.length, "Random points array should have a length of 3");
    }

}
