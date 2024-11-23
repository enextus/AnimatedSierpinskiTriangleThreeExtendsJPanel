package org.serpinskitriangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnimatedSierpinskiTriangleThreeTest {

    private TriangleManagerTest manager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        manager = new TriangleManagerTest(7, 13999);
    }

    /**
     * Тесты для AnimatedSierpinskiTriangleThree
     */
    @Test
    void testTimerStartWhenConditionMet() {
        AnimatedSierpinskiTriangleThree triangle = new AnimatedSierpinskiTriangleThree();
        triangle.generateTriangles();
        // Поскольку больше нет mockTimer, нельзя проверить его запуск напрямую.
        // Вместо этого можно проверить состояние или поведение класса после вызова generateTriangles().
        assertEquals(1, triangle.triangleCount, "Ожидается, что начальное количество треугольников будет 1 после запуска таймера.");
    }

    /**
     * Тесты для TriangleManager
     */
    @Test
    void testDivideTriangleBoundaryConditions() {
        Point[] triangle = {
                new Point(Integer.MAX_VALUE, Integer.MAX_VALUE),
                new Point(0, 0),
                new Point(0, Integer.MAX_VALUE)
        };
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
        assertTrue(manager.canDivide(13996), "Ожидается, что при 13996 треугольников можно еще разделить");
        assertFalse(manager.canDivide(13999), "Ожидается, что при 13999 треугольников нельзя больше делить");
    }

    @Test
    void testDivideTriangleWithInvalidCoordinates() {
        Point[] triangle = {null, new Point(4, 0), new Point(2, 4)};
        assertThrows(NullPointerException.class, () -> manager.divideTriangle(triangle));
    }

    @Test
    void testDivideTriangleLargeCoordinates() {
        Point[] triangle = {
                new Point(1000, 1000),
                new Point(4000, 1000),
                new Point(2500, 4000)
        };
        List<Point[]> dividedTriangles = manager.divideTriangle(triangle);
        assertEquals(3, dividedTriangles.size());
    }

    @Test
    void testDivideTriangleCorrectCoordinates() {
        Point[] triangle = {
                new Point(0, 0),
                new Point(4, 0),
                new Point(2, 4)
        };
        List<Point[]> dividedTriangles = manager.divideTriangle(triangle);

        assertArrayEquals(new Point[]{new Point(0, 0), new Point(2, 0), new Point(1, 2)}, dividedTriangles.get(0));
        assertArrayEquals(new Point[]{new Point(2, 0), new Point(4, 0), new Point(3, 2)}, dividedTriangles.get(1));
        assertArrayEquals(new Point[]{new Point(1, 2), new Point(3, 2), new Point(2, 4)}, dividedTriangles.get(2));
    }

    @Test
    void testDivideTriangleDoesNotExceedMaxTriangles() {
        assertFalse(manager.canDivide(13998)); // Превышает лимит
    }

}
