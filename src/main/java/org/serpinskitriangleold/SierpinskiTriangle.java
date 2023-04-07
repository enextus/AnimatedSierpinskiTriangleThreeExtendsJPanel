package org.serpinskitriangleold;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SierpinskiTriangle extends JPanel {

    private static final int MAX_DEPTH = 3;
    private static final Color[] COLOR_MAP = {Color.BLUE, Color.RED, Color.GREEN, Color.WHITE, Color.YELLOW, Color.MAGENTA, Color.ORANGE};

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sierpinski Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setContentPane(new SierpinskiTriangle());
        frame.setVisible(true);
    }

    private void drawTriangle(Point[] points, int depth, Graphics g) {
        g.setColor(COLOR_MAP[depth]);
        int[] x = {(int) points[0].getX(), (int) points[1].getX(), (int) points[2].getX()};
        int[] y = {(int) points[0].getY(), (int) points[1].getY(), (int) points[2].getY()};
        g.fillPolygon(x, y, 3);
        if (depth < MAX_DEPTH) {
            drawTriangle(new Point[]{points[0], midpoint(points[0], points[1]), midpoint(points[0], points[2])}, depth + 1, g);
            drawTriangle(new Point[]{points[1], midpoint(points[0], points[1]), midpoint(points[1], points[2])}, depth + 1, g);
            drawTriangle(new Point[]{points[2], midpoint(points[2], points[1]), midpoint(points[0], points[2])}, depth + 1, g);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Point[] points = {new Point(100, 500), new Point(300, 100), new Point(500, 500)};
        drawTriangle(points, 0, g);
    }

    private Point midpoint(Point p1, Point p2) {
        int x = (int) ((p1.getX() + p2.getX()) / 2);
        int y = (int) ((p1.getY() + p2.getY()) / 2);
        return new Point(x, y);
    }

}
