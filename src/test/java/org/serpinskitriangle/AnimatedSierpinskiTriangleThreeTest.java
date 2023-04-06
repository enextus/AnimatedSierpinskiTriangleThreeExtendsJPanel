package org.serpinskitriangle;

import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AnimatedSierpinskiTriangleThreeTest {

    @Test
    public void testMidpoint() {
        AnimatedSierpinskiTriangleThree triangle = new AnimatedSierpinskiTriangleThree();
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        Point midpoint = triangle.midpoint(p1, p2);
        assertEquals(5, midpoint.x);
        assertEquals(5, midpoint.y);
    }

    @Test
    public void testGetInitialTriangle() {
        AnimatedSierpinskiTriangleThree triangle = new AnimatedSierpinskiTriangleThree();
        Point[] initialTriangle = triangle.getInitialTriangle(600, 600);
        assertNotNull(initialTriangle);
        assertEquals(3, initialTriangle.length);
        assertEquals(new Point(300, 0), initialTriangle[0]);
        assertEquals(new Point(0, 600), initialTriangle[1]);
        assertEquals(new Point(600, 600), initialTriangle[2]);
    }
}
