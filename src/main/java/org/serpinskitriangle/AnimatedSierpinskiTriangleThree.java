package org.serpinskitriangle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AnimatedSierpinskiTriangleThree extends JPanel {
    public static final int MAX_DEPTH = 6;
    public static final int MAX_TRIANGLES = 8645;
    public static final Color[] COLOR_MAP = {Color.BLUE, Color.RED, Color.GREEN, Color.WHITE, Color.YELLOW, Color.MAGENTA, Color.ORANGE};
    private static final int DELAY = 100;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private static final int FONT_SIZE = 22;

    final List<Point[]> triangles = new ArrayList<>();
    int triangleCount = 1;
    final JLabel countLabel = new JLabel("Triangles: 1");
    private final Timer timer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Animated Sierpinski Triangle Three");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(WIDTH, HEIGHT);
            AnimatedSierpinskiTriangleThree contentPane = new AnimatedSierpinskiTriangleThree();
            frame.setContentPane(contentPane);
            frame.setVisible(true);
            contentPane.generateTriangles();
        });
    }

    public AnimatedSierpinskiTriangleThree() {
        this.timer = new Timer(DELAY, e -> divideTriangle(getInitialTriangle(WIDTH, HEIGHT), 0));
        countLabel.setFont(new Font(countLabel.getFont().getName(), Font.PLAIN, FONT_SIZE));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());
        add(countLabel, BorderLayout.NORTH);
    }

    public void generateTriangles() {
        Point[] points = getInitialTriangle(WIDTH, HEIGHT);
        triangles.add(points);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < triangles.size(); i++) {
            g.setColor(COLOR_MAP[i % COLOR_MAP.length]);
            int[] x = {(int) triangles.get(i)[0].getX(), (int) triangles.get(i)[1].getX(), (int) triangles.get(i)[2].getX()};
            int[] y = {(int) triangles.get(i)[0].getY(), (int) triangles.get(i)[1].getY(), (int) triangles.get(i)[2].getY()};
            g.fillPolygon(x, y, 3);
        }
    }

    void divideTriangle(Point[] points, int depth) {
        if (depth < MAX_DEPTH && triangleCount + 3 <= MAX_TRIANGLES) {
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

            Timer newTimer = new Timer(DELAY, e -> {
                divideTriangle(triangle1, depth + 1);
                divideTriangle(triangle2, depth + 1);
                divideTriangle(triangle3, depth + 1);
            });
            newTimer.setRepeats(false);
            newTimer.start();
        } else {
            timer.stop();
        }
    }

    public static Point[] getInitialTriangle(int width, int height) {
        return new Point[]{
                new Point(width / 2, 0),
                new Point(0, height),
                new Point(width, height)
        };
    }

    public static Point midpoint(Point p1, Point p2) {
        int x = (p1.x + p2.x) / 2;
        int y = (p1.y + p2.y) / 2;
        return new Point(x, y);
    }

}
