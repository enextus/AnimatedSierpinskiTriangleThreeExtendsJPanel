package org.serpinskitriangleold;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OptimizedSierpinskiTriangle extends JPanel {

    private static final int MAX_DEPTH = 6;
    private static final Color[] COLOR_MAP = {Color.BLUE, Color.RED, Color.GREEN, Color.WHITE, Color.YELLOW, Color.MAGENTA, Color.ORANGE};

    private ArrayList<Point[]> triangles = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Optimized Sierpinski Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        OptimizedSierpinskiTriangle contentPane = new OptimizedSierpinskiTriangle();
        frame.setContentPane(contentPane);
        frame.setVisible(true);
        contentPane.generateTriangles();
    }

    public void generateTriangles() {
        Point[] points = getRandomPoints();
        divideTriangle(points, 0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Point[] triangle : triangles) {
            g.setColor(COLOR_MAP[triangles.indexOf(triangle) % COLOR_MAP.length]);
            int[] x = {(int) triangle[0].getX(), (int) triangle[1].getX(), (int) triangle[2].getX()};
            int[] y = {(int) triangle[0].getY(), (int) triangle[1].getY(), (int) triangle[2].getY()};
            g.fillPolygon(x, y, 3);
        }
    }

    private void divideTriangle(Point[] points, int depth) {
        if (depth < MAX_DEPTH) {
            triangles.add(points);

            Point p1 = midpoint(points[0], points[1]);
            Point p2 = midpoint(points[1], points[2]);
            Point p3 = midpoint(points[0], points[2]);

            divideTriangle(new Point[]{points[0], p1, p3}, depth + 1);
            divideTriangle(new Point[]{p1, points[1], p2}, depth + 1);
            divideTriangle(new Point[]{p3, p2, points[2]}, depth + 1);
        }
    }

    private Point[] getRandomPoints() {
        Random rand = new Random();
        Point[] points = new Point[3];
        int width = getWidth();
        int height = getHeight();
        for (int i = 0; i < 3; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            points[i] = new Point(x, y);
        }
        return points;
    }

    Point midpoint(Point p1, Point p2) {
        int x = (int) ((p1.getX() + p2.getX()) / 2);
        int y = (int) ((p1.getY() + p2.getY()) / 2);
        return new Point(x, y);
    }

}
