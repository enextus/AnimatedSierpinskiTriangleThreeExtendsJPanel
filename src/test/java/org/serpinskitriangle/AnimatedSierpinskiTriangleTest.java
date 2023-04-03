package org.serpinskitriangle;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AnimatedSierpinskiTriangleTest {

    private AnimatedSierpinskiTriangle triangle;

    @BeforeEach
    void setUp() {
        triangle = new AnimatedSierpinskiTriangle();
    }

    @Test
    void testGetRandomPoints() {
        Point[] points = triangle.getRandomPoints();
        assertNotNull(points);
        assertEquals(3, points.length);
        assertNotNull(points[0]);
        assertNotNull(points[1]);
        assertNotNull(points[2]);
    }

    @Test
    void testMidpoint() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(2, 0);
        Point p3 = new Point(1, 1);
        Point mp1 = triangle.midpoint(p1, p2);
        Point mp2 = triangle.midpoint(p1, p3);
        Point mp3 = triangle.midpoint(p2, p3);
        assertEquals(1, mp1.getX());
        assertEquals(0, mp1.getY());
        assertEquals(0, mp2.getX());
        assertEquals(0, mp2.getY());
        assertEquals(1, mp3.getX());
        assertEquals(1, mp3.getY());
    }

    @Test
    void testDivideTriangle() {
        Point[] points = {new Point(0, 0), new Point(600, 0), new Point(300, 600)};
        triangle.divideTriangle(points, 0);
        ArrayList<Point[]> triangles = triangle.getTriangles();
        assertNotNull(triangles);
        assertEquals(7, triangles.size());
        assertArrayEquals(points, triangles.get(0));
        assertEquals(new Point(300, 0), triangles.get(1)[1]);
        assertEquals(new Point(150, 300), triangles.get(2)[2]);
        assertEquals(new Point(450, 300), triangles.get(3)[0]);
        assertEquals(new Point(225, 150), triangles.get(4)[1]);
        assertEquals(new Point(375, 150), triangles.get(5)[0]);
        assertEquals(new Point(300, 300), triangles.get(6)[2]);
    }

    @Test
    void testPaintComponent() {
        Graphics graphics = Mockito.mock(Graphics.class);
        JPanel panel = Mockito.spy(new JPanel());
        Mockito.when(panel.getWidth()).thenReturn(600);
        Mockito.when(panel.getHeight()).thenReturn(600);
        triangle.paintComponent(graphics);
        Mockito.verify(graphics, Mockito.times(7)).setColor(Mockito.any());
        Mockito.verify(graphics, Mockito.times(7)).fillPolygon(Mockito.any(), Mockito.any(), Mockito.anyInt());
    }
}
