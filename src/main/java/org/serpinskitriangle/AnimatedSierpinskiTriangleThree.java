package org.serpinskitriangle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AnimatedSierpinskiTriangleThree extends JPanel {

	protected static final int MAX_DEPTH = 6;
	protected static final int MAX_TRIANGLES = 7645;
	protected static final Color[] COLOR_MAP = {Color.BLUE, Color.RED, Color.GREEN, Color.WHITE, Color.YELLOW, Color.MAGENTA, Color.ORANGE};
	protected static final Color[] COLOR_MAP_TWO = {Color.BLACK};
	protected static final int DELAY = 700;
	protected static final int WIDTH = 1000;
	protected static final int HEIGHT = 1000;
	protected static final int FONT_SIZE = 22;
	protected final ArrayList<Point[]> triangles = new ArrayList<>();
	protected int triangleCount = 1;
	protected final JLabel countLabel = new JLabel("Triangles: 1");

	public static void main(String[] args) {
		JFrame frame = new JFrame("Animated Sierpinski Triangle Three");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		AnimatedSierpinskiTriangleThree contentPane = new AnimatedSierpinskiTriangleThree();
		frame.setContentPane(contentPane);
		frame.setVisible(true);

		contentPane.generateTriangles();
	}

	public AnimatedSierpinskiTriangleThree() {
		Font currentFont = countLabel.getFont();
		Font newFont = currentFont.deriveFont((float) FONT_SIZE);
		countLabel.setFont(newFont);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new BorderLayout());
		add(countLabel, BorderLayout.NORTH);
	}

	public void generateTriangles() {
		Point[] points = getInitialTriangle(WIDTH, HEIGHT);
		triangles.add(points);  // Добавляем исходный треугольник
		Timer timer = new Timer(DELAY, e -> divideTriangle(points, 0, (Timer) e.getSource()));
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int dotRadius = 4;

		for (int i = 0; i < triangles.size(); i++) {
			g.setColor(COLOR_MAP_TWO[i % COLOR_MAP_TWO.length]);

			for (Point point : triangles.get(i)) {
				int x = (int) point.getX() - dotRadius / 2;
				int y = (int) point.getY() - dotRadius / 2;
				g.fillOval(x, y, dotRadius, dotRadius);
			}

			g.setColor(COLOR_MAP[i % COLOR_MAP.length]);
			int[] x = {(int) triangles.get(i)[0].getX(), (int) triangles.get(i)[1].getX(), (int) triangles.get(i)[2].getX()};
			int[] y = {(int) triangles.get(i)[0].getY(), (int) triangles.get(i)[1].getY(), (int) triangles.get(i)[2].getY()};
			g.fillPolygon(x, y, 3);
		}
	}

	protected void divideTriangle(Point[] points, int depth, Timer timer) {
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
				divideTriangle(triangle1, depth + 1, timer);
				divideTriangle(triangle2, depth + 1, timer);
				divideTriangle(triangle3, depth + 1, timer);
			});
			newTimer.setRepeats(false);
			newTimer.start();
		} else {
			if (timer != null) {
				timer.stop();
			}
		}
	}

	protected static Point[] getInitialTriangle(int width, int height) {
		return new Point[]{
				new Point(width / 2, 0),
				new Point(0, height),
				new Point(width, height)
		};
	}

	protected static Point midpoint(Point p1, Point p2) {
		int x = (int) ((p1.getX() + p2.getX()) / 2);
		int y = (int) ((p1.getY() + p2.getY()) / 2);
		return new Point(x, y);
	}
}
