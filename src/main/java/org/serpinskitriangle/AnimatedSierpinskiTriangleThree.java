package org.serpinskitriangle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class AnimatedSierpinskiTriangleThree extends JPanel {

    private static final int MAX_DEPTH = 6;
    private static final Color[] COLOR_MAP = {Color.BLUE, Color.RED, Color.GREEN, Color.WHITE, Color.YELLOW, Color.MAGENTA, Color.ORANGE};
    private ArrayList<Point[]> triangles = new ArrayList<>();
    private int triangleCount = 1;
    private JLabel countLabel = new JLabel("Triangles: 1");

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animated Sierpinski Triangle Three");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        AnimatedSierpinskiTriangleThree contentPane = new AnimatedSierpinskiTriangleThree();
        frame.setContentPane(contentPane);
        frame.setVisible(true);
        contentPane.generateTriangles();
    }

    public AnimatedSierpinskiTriangleThree() {
        setPreferredSize(new Dimension(990, 990));
        setLayout(null); // Disable the layout manager so we can place components manually
        add(countLabel);
        countLabel.setBounds(getWidth() / 2 - 40, getHeight() - 40, 100, 20); // Set the label location
    }

    public void generateTriangles() {
        Point[] points = getInitialTriangle(getWidth(), getHeight());
        triangles.add(points);
        divideTriangle(points, 0);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < triangles.size(); i++) {
            g.setColor(COLOR_MAP[i % COLOR_MAP.length]);
            int[] x = {(int) triangles.get(i)[0].getX(), (int) triangles.get(i)[1].getX(), (int) triangles.get(i)[2].getX()};
            int[] y = {(int) triangles.get(i)[0].getY(), (int) triangles.get(i)[1].getY(), (int) triangles.get(i)[2].getY()};
            g.fillPolygon(x, y, 3);
        }
    }

    void divideTriangle(Point[] points, int depth) {
        if (depth < MAX_DEPTH) {
            Point p1 = midpoint(points[0], points[1]);
            Point p2 = midpoint(points[1], points[2]);
            Point p3 = midpoint(points[0], points[2]);
            Point[] triangle1 = {points[0], p1, p3};
            Point[] triangle2 = {p1, points[1], p2};
            Point[] triangle3 = {p3, p2, points[2]};
            triangles.add(triangle1);
            triangles.add(triangle2);
            triangles.add(triangle3);
            triangleCount += 3;
            countLabel.setText("Triangles: " + triangleCount);
            repaint();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            divideTriangle(triangle1, depth + 1);
            divideTriangle(triangle2, depth + 1);
            divideTriangle(triangle3, depth + 1);
        }
    }

    public ArrayList<Point[]> getTriangles() {
        return triangles;
    }

    /**
     * Метод, который генерирует начальный равносторонний треугольник,
     * который занимает большую часть панели.
     *
     * @param width  ширина панели
     * @param height высота панели
     * @return массив точек, представляющих начальный треугольник
     */
    Point[] getInitialTriangle(int width, int height) {
        Point[] points = new Point[3];
        points[0] = new Point(width / 2, 0);
        points[1] = new Point(0, height);
        points[2] = new Point(width, height);
        return points;
    }

    /**
     * Метод, который генерирует массив из трех случайных точек в пределах границ панели.
     *
     * @param width  ширина панели
     * @param height высота панели
     * @return массив точек, представляющих случайный треугольник
     */
    Point[] getRandomPoints(int width, int height) {
        Point[] points = new Point[3];
        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            points[i] = new Point(x, y);
        }
        return points;
    }

    /**
     * Метод, который вычисляет и возвращает середину между двумя точками.
     *
     * @param p1 первая точка
     * @param p2 вторая точка
     * @return точка, представляющая середину между двумя точками
     */
    Point midpoint(Point p1, Point p2) {
        int x = (int) ((p1.getX() + p2.getX()) / 2);
        int y = (int) ((p1.getY() + p2.getY()) / 2);
        return new Point(x, y);
    }

    /**
     * Метод, который возвращает countLabel.
     *
     * @return JLabel, представляющий countLabel
     */
    public JLabel getTriangleCountLabel() {
        return countLabel;
    }

    /**
     * Метод, который возвращает текущее количество треугольников.
     *
     * @return текущее количество треугольников
     */
    public int getTriangleCount() {
        return triangleCount;
    }
}