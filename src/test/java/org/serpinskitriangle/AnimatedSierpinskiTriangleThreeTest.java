package org.serpinskitriangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.serpinskitriangle.AnimatedSierpinskiTriangleThree.MAX_DEPTH;
import static org.serpinskitriangle.AnimatedSierpinskiTriangleThree.MAX_TRIANGLES;

/**
 * Test class for AnimatedSierpinskiTriangleThree.
 * This class contains unit tests to validate the functionality of the AnimatedSierpinskiTriangleThree class.
 */
@ExtendWith(MockitoExtension.class)
class AnimatedSierpinskiTriangleThreeTest {

    @InjectMocks
    private AnimatedSierpinskiTriangleThree triangle; // Объект, который будем тестировать, с автоматическим внедрением зависимостей

    @Mock
    private Timer mockTimer; // Создаем мок объекта Timer

    /**
     * Set up method to initialize the AnimatedSierpinskiTriangleThree object before each test.
     */
    @BeforeEach
    void setUp() {
        triangle = new AnimatedSierpinskiTriangleThree();
    }

    /**
     * Test for the getInitialTriangle method to verify that it returns the correct initial triangle points.
     */
    @Test
    void getInitialTriangle() {
        Point[] initialTriangle = AnimatedSierpinskiTriangleThree.getInitialTriangle(600, 600);
        assertNotNull(initialTriangle);
        assertEquals(3, initialTriangle.length);
        assertEquals(new Point(300, 0), initialTriangle[0]);
        assertEquals(new Point(0, 600), initialTriangle[1]);
        assertEquals(new Point(600, 600), initialTriangle[2]);
    }

    /**
     * Test for the midpoint method to verify that it calculates the midpoint correctly between two points.
     */
    @Test
    void midpoint() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        Point mid = AnimatedSierpinskiTriangleThree.midpoint(p1, p2);
        assertNotNull(mid);
        assertEquals(new Point(5, 5), mid);
    }

    /**
     * Test for the static midpoint method.
     */
    @Test
    void testMidpoint() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 4);
        Point midpoint = AnimatedSierpinskiTriangleThree.midpoint(p1, p2);
        assertEquals(new Point(2, 2), midpoint);
    }

    /**
     * Test for the static getInitialTriangle method.
     */
    @Test
    void testGetInitialTriangle() {
        Point[] initialTriangle = AnimatedSierpinskiTriangleThree.getInitialTriangle(1000, 1000);
        assertEquals(new Point(500, 0), initialTriangle[0]);
        assertEquals(new Point(0, 1000), initialTriangle[1]);
        assertEquals(new Point(1000, 1000), initialTriangle[2]);
    }

    /**
     * Test for dividing a triangle at depth 0.
     */
    @Test
    void testDivideTriangleDepthZero() {
        Point[] points = {new Point(0, 0), new Point(4, 0), new Point(2, 4)};
        Timer dummyTimer = new Timer(1000, e -> {}); // Создаем фиктивный таймер
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
