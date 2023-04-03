package org.serpinskitriangle;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RandomSierpinskiTriangle extends JPanel {

    private static final int MAX_DEPTH = 6;
    private static final Color[] COLOR_MAP = {Color.BLUE, Color.RED, Color.GREEN, Color.WHITE, Color.YELLOW, Color.MAGENTA, Color.ORANGE};

    public static void main(String[] args) {
        JFrame frame = new JFrame("Random Sierpinski Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setContentPane(new RandomSierpinskiTriangle());
        frame.setVisible(true);
    }

    private void drawTriangle(Point[] points, int depth, Graphics g) {
        g.setColor(COLOR_MAP[depth % COLOR_MAP.length]);
        int[] x = {(int) points[0].getX(), (int) points[1].getX(), (int) points[2].getX()};
        int[] y = {(int) points[0].getY(), (int) points[1].getY(), (int) points[2].getY()};
        g.fillPolygon(x, y, 3);
        if (depth < MAX_DEPTH) {
            ArrayList<Point[]> triangles = divideTriangle(points);
            for (Point[] triangle : triangles) {
                drawTriangle(triangle, depth + 1, g);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Point[] points = getRandomPoints();
        drawTriangle(points, 0, g);
    }

    private Point[] getRandomPoints() {
        Random rand = new Random();
        Point[] points = new Point[3];
        for (int i = 0; i < 3; i++) {
            int x = rand.nextInt(getWidth());
            int y = rand.nextInt(getHeight());
            points[i] = new Point(x, y);
        }
        return points;
    }

    private ArrayList<Point[]> divideTriangle(Point[] points) {
        ArrayList<Point[]> triangles = new ArrayList<>();
        Point p1 = midpoint(points[0], points[1]);
        Point p2 = midpoint(points[1], points[2]);
        Point p3 = midpoint(points[0], points[2]);
        triangles.add(new Point[]{points[0], p1, p3});
        triangles.add(new Point[]{p1, points[1], p2});
        triangles.add(new Point[]{p3, p2, points[2]});
        return triangles;
    }

    private Point midpoint(Point p1, Point p2) {
        int x = (int) ((p1.getX() + p2.getX()) / 2);
        int y = (int) ((p1.getY() + p2.getY()) / 2);
        return new Point(x, y);
    }

}
