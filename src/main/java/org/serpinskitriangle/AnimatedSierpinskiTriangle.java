package org.serpinskitriangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AnimatedSierpinskiTriangle extends JPanel {

    private static final int MAX_DEPTH = 6;
    private static final Color[] COLOR_MAP = {Color.BLUE, Color.RED, Color.GREEN, Color.WHITE, Color.YELLOW, Color.MAGENTA, Color.ORANGE};

    private ArrayList<Point[]> triangles = new ArrayList<>();
    private int depth = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animated Sierpinski Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setContentPane(new AnimatedSierpinskiTriangle());
        frame.setVisible(true);
    }

    public AnimatedSierpinskiTriangle() {
        Point[] points = getRandomPoints();
        triangles.add(points);
        divideTriangle(points, depth);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i <= depth; i++) {
            g.setColor(COLOR_MAP[i % COLOR_MAP.length]);
            int[] x = {(int) triangles.get(i)[0].getX(), (int) triangles.get(i)[1].getX(), (int) triangles.get(i)[2].getX()};
            int[] y = {(int) triangles.get(i)[0].getY(), (int) triangles.get(i)[1].getY(), (int) triangles.get(i)[2].getY()};
            g.fillPolygon(x, y, 3);
        }
    }

    private void divideTriangle(Point[] points, int depth) {
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
            this.depth++;
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

    private Point[] getRandomPoints() {
        Random rand = new Random();
        Point[] points = new Point[3];
        int width = getWidth();
        int height = getHeight();
        if (width > 0 && height > 0) {
            for (int i = 0; i < 3; i++) {
                System.out.println(i);
                int x = rand.nextInt(width);
                int y = rand.nextInt(height);
                points[i] = new Point(x, y);
            }
        } else {
            // Если значения ширины или высоты панели равны нулю,
            // то генерируются случайные координаты от 0 до 600
            for (int i = 0; i < 3; i++) {
                int x = rand.nextInt(600);
                int y = rand.nextInt(600);
                points[i] = new Point(x, y);
            }
        }
        return points;
    }


    private Point midpoint(Point p1, Point p2) {
        int x = (int) ((p1.getX() + p2.getX()) / 2);
        int y = (int) ((p1.getY() + p2.getY()) / 2);
        return new Point(x, y);
    }

}
