package org.serpinskitriangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TriangleManagerStepDefinitionsTest {

    private TriangleManagerTest manager;

    @Mock
    private javax.swing.Timer mockTimer;

    private Point[] initialTriangle;
    private Point midpoint;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        manager = new TriangleManagerTest(7, 13999);
    }

    @Test
    void testDivideTriangleBoundaryConditions() {
        Point[] triangle = {new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), new Point(0, 0), new Point(0, Integer.MAX_VALUE)};
        List<Point[]> dividedTriangles = manager.divideTriangle(triangle);
        assertEquals(3, dividedTriangles.size());
    }

    @Test
    void testInitialTriangleSmallPanel() {
        Point[] triangle = manager.getInitialTriangle(10, 10);
        assertEquals(new Point(5, 0), triangle[0]);
        assertEquals(new Point(0, 10), triangle[1]);
        assertEquals(new Point(10, 10), triangle[2]);
    }

    @Test
    void testBoundaryMaxDepthBehavior() {
        assertTrue(manager.canDivide(13996));
        assertFalse(manager.canDivide(13999));
    }

    @Test
    void testDivideTriangleWithInvalidCoordinates() {
        Point[] triangle = {null, new Point(4, 0), new Point(2, 4)};
        assertThrows(NullPointerException.class, () -> manager.divideTriangle(triangle));
    }

    @Test
    void testTimerMultipleStarts() {
        doNothing().when(mockTimer).start();
        for (int i = 0; i < 10; i++) {
            mockTimer.start();
        }
        verify(mockTimer, times(10)).start();
    }

    @Test
    void testDivideTriangleLargeCoordinates() {
        Point[] triangle = {new Point(1000, 1000), new Point(4000, 1000), new Point(2500, 4000)};
        List<Point[]> dividedTriangles = manager.divideTriangle(triangle);

        assertEquals(3, dividedTriangles.size());
    }

    @Test
    void testDivideTriangleCorrectCoordinates() {
        Point[] triangle = {new Point(0, 0), new Point(4, 0), new Point(2, 4)};
        List<Point[]> dividedTriangles = manager.divideTriangle(triangle);

        assertArrayEquals(new Point[]{new Point(0, 0), new Point(2, 0), new Point(1, 2)}, dividedTriangles.get(0));
        assertArrayEquals(new Point[]{new Point(2, 0), new Point(4, 0), new Point(3, 2)}, dividedTriangles.get(1));
        assertArrayEquals(new Point[]{new Point(1, 2), new Point(3, 2), new Point(2, 4)}, dividedTriangles.get(2));
    }

    @Test
    void testDivideTriangleDoesNotExceedMaxTriangles() {
        Point[] triangle = {new Point(0, 0), new Point(4, 0), new Point(2, 4)};
        assertFalse(manager.canDivide(13998)); // Превышает лимит
    }

    /**
     * Test for midpoint calculation between two points.
     */
    @Test
    void testMidpointCalculation() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        Point expectedMidpoint = new Point(5, 5);
        Point actualMidpoint = manager.midpoint(p1, p2);
        assertEquals(expectedMidpoint, actualMidpoint, "Midpoint should be (5, 5)");
    }

    /**
     * Test for generating initial triangle with larger dimensions.
     */
    @Test
    void testGenerateInitialTriangle() {
        Point[] triangle = manager.getInitialTriangle(800, 800);
        assertArrayEquals(new Point[]{new Point(400, 0), new Point(0, 800), new Point(800, 800)}, triangle);
    }
}
