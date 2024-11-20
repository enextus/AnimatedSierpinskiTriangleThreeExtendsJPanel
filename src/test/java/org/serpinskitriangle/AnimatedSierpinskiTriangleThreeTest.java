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
        triangle.triangles.clear(); // Очистка списка треугольников
        triangle.triangles.add(points); // Добавление исходного треугольника
        triangle.divideTriangle(points, 0, dummyTimer);
        assertEquals(4, triangle.triangles.size());
    }

    /**
     * Test for dividing a triangle at maximum depth.
     */
    @Test
    void testDivideTriangleMaxDepth() {
        Point[] points = {new Point(0, 0), new Point(4, 0), new Point(2, 4)};
        Timer dummyTimer = new Timer(1000, e -> {}); // Создаем фиктивный таймер
        triangle.triangles.clear(); // Очистка списка треугольников
        triangle.triangles.add(points); // Добавление исходного треугольника
        triangle.divideTriangle(points, 6, dummyTimer);
        assertEquals(1, triangle.triangles.size());
    }

    /**
     * Test to ensure triangle count does not exceed the maximum allowed.
     */
    @Test
    void testDivideTriangleExceedMaxTriangles() {
        triangle.triangleCount = 499999;
        Point[] points = {new Point(0, 0), new Point(4, 0), new Point(2, 4)};
        Timer dummyTimer = new Timer(1000, e -> {}); // Создаем фиктивный таймер
        triangle.triangles.clear(); // Очистка списка треугольников
        triangle.triangles.add(points); // Добавление исходного треугольника
        triangle.divideTriangle(points, 0, dummyTimer);
        assertEquals(1, triangle.triangles.size());
    }

    /**
     * Test for generating the initial set of triangles.
     */
    @Test
    void testGenerateTriangles() {
        triangle.generateTriangles();
        assertEquals(1, triangle.triangles.size());
    }

    /**
     * Test for rendering the triangles.
     */
    @Test
    void testPaintComponent() {
        JFrame frame = new JFrame();
        frame.add(triangle);
        frame.setSize(200, 200);
        frame.setVisible(true);

        triangle.generateTriangles();
        triangle.repaint(); // Добавляем вызов repaint()
        triangle.paintComponent(frame.getGraphics());
        assertEquals(1, triangle.triangles.size());
    }

    /**
     * Test for verifying the points of the initial triangle.
     */
    @Test
    void testInitialTrianglePoints() {
        Point[] points = AnimatedSierpinskiTriangleThree.getInitialTriangle(800, 800);
        assertEquals(new Point(400, 0), points[0]);
        assertEquals(new Point(0, 800), points[1]);
        assertEquals(new Point(800, 800), points[2]);
    }

    /**
     * Test for verifying the calculation of the midpoint.
     */
    @Test
    void testMidpointCalculation() {
        Point p1 = new Point(100, 200);
        Point p2 = new Point(300, 400);
        Point mid = AnimatedSierpinskiTriangleThree.midpoint(p1, p2);
        assertEquals(new Point(200, 300), mid);
    }

    /**
     * Test for verifying the color mapping of triangles.
     */
    @Test
    void testTriangleColorMapping() {
        assertEquals(Color.BLUE, AnimatedSierpinskiTriangleThree.COLOR_MAP[0]);
        assertEquals(Color.RED, AnimatedSierpinskiTriangleThree.COLOR_MAP[1]);
    }

    /**
     * Test for adding a triangle to the list.
     */
    @Test
    void testTriangleAddition() {
        Point[] points = {new Point(0, 0), new Point(4, 0), new Point(2, 4)};
        triangle.triangles.add(points);
        assertEquals(1, triangle.triangles.size());
    }

    /**
     * Test for verifying the triangle count label.
     */
    @Test
    void testTriangleCountLabel() {
        assertEquals("Triangles: 1", triangle.countLabel.getText());
        triangle.triangleCount = 10;
        triangle.countLabel.setText("Triangles: " + triangle.triangleCount);
        assertEquals("Triangles: 10", triangle.countLabel.getText());
    }

    /**
     * Test for incrementing the triangle count.
     */
    @Test
    void testTriangleCountIncrement() {
        triangle.triangleCount = 10;
        triangle.triangleCount += 3;
        assertEquals(13, triangle.triangleCount);
    }

    /**
     * Test for verifying the maximum depth constant.
     */
    @Test
    void testMaxDepthConstant() {
        assertEquals(6, MAX_DEPTH);
    }

    /**
     * Test for verifying the maximum triangles constant.
     */
    @Test
    void testMaxTrianglesConstant() {
        assertEquals(8645, MAX_TRIANGLES);
    }



}
