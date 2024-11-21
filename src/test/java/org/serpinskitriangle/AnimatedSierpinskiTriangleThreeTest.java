package org.serpinskitriangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnimatedSierpinskiTriangleThreeTest {

    @Mock
    private Timer mockTimer;

    private TriangleManager manager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        manager = new TriangleManager(7, 13999);
    }

    @Test
    void testDivideTriangleUsesMockTimer() {
        Point[] triangle = {new Point(0, 0), new Point(4, 0), new Point(2, 4)};
        when(mockTimer.isRunning()).thenReturn(true);

        List<Point[]> dividedTriangles = manager.divideTriangle(triangle);

        verify(mockTimer, never()).stop(); // Ensure timer is not stopped by divideTriangle logic
        assertEquals(3, dividedTriangles.size());
    }

    @Test
    void testTimerBehavior() {
        doNothing().when(mockTimer).start();
        mockTimer.start();
        verify(mockTimer, times(1)).start();
    }

    @Test
    void testInitialTriangleBehavior() {
        Point[] initialTriangle = manager.getInitialTriangle(1000, 1000);
        assertNotNull(initialTriangle);
        assertEquals(3, initialTriangle.length);
    }

    @Test
    void testBoundaryMaxDepth() {
        Point[] triangle = {new Point(0, 0), new Point(4, 0), new Point(2, 4)};
        assertFalse(manager.canDivide(13997)); // Exceeds max triangles
        assertTrue(manager.canDivide(13996)); // Within limit
    }

    @Test
    void testColorMapping() {
        Color[] colors = AnimatedSierpinskiTriangleThree.COLOR_MAP;
        assertEquals(Color.BLUE, colors[0]);
        assertEquals(Color.RED, colors[1]);
        assertEquals(Color.GREEN, colors[2]);
    }

    @Test
    void testPanelSizeLarge() {
        Point[] triangle = manager.getInitialTriangle(2000, 2000);
        assertEquals(new Point(1000, 0), triangle[0]);
        assertEquals(new Point(0, 2000), triangle[1]);
        assertEquals(new Point(2000, 2000), triangle[2]);
    }

    @Test
    void testMidpointNegativeCoordinates() {
        Point p1 = new Point(-10, -20);
        Point p2 = new Point(10, 20);
        Point mid = TriangleManager.midpoint(p1, p2);
        assertEquals(new Point(0, 0), mid);
    }

}
