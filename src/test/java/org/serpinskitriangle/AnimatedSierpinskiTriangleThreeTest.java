package org.serpinskitriangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.*;

class AnimatedSierpinskiTriangleThreeTest {

    private AnimatedSierpinskiTriangleThree triangle;

    @BeforeEach
    void setUp() {
        triangle = new AnimatedSierpinskiTriangleThree();
    }

    @Test
    void getInitialTriangle() {
        Point[] initialTriangle = triangle.getInitialTriangle(600, 600);
        assertNotNull(initialTriangle);
        assertEquals(3, initialTriangle.length);
        assertEquals(new Point(300, 0), initialTriangle[0]);
        assertEquals(new Point(0, 600), initialTriangle[1]);
        assertEquals(new Point(600, 600), initialTriangle[2]);
    }

    @Test
    void midpoint() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        Point mid = triangle.midpoint(p1, p2);
        assertNotNull(mid);
        assertEquals(new Point(5, 5), mid);
    }
}
