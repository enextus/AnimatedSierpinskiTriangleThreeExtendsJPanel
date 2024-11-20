package org.serpinskitriangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class AnimatedSierpinskiTriangleThreeTest {

    private TriangleManager manager;

    @BeforeEach
    void setUp() {
        manager = new TriangleManager(7, 13999);
    }

    @Test
    void testGetInitialTriangle() {
        Point[] triangle = manager.getInitialTriangle(800, 800);
        assertNotNull(triangle);
        assertEquals(new Point(400, 0), triangle[0]);
        assertEquals(new Point(0, 800), triangle[1]);
        assertEquals(new Point(800, 800), triangle[2]);
    }

    @Test
    void testMidpoint() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        Point mid = TriangleManager.midpoint(p1, p2);
        assertEquals(new Point(5, 5), mid);
    }

    @Test
    void testDivideTriangle() {
        Point[] triangle = {new Point(0, 0), new Point(4, 0), new Point(2, 4)};
        var dividedTriangles = manager.divideTriangle(triangle);
        assertEquals(3, dividedTriangles.size());
    }

    @Test
    void testMaxTriangles() {
        assertTrue(manager.canDivide(13996));
        assertFalse(manager.canDivide(13997));
    }
}
