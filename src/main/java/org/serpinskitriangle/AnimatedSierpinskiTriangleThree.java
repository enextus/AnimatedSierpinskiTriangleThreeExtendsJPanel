package org.serpinskitriangle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AnimatedSierpinskiTriangleThree class that generates and animates a Sierpinski triangle.
 * Extends JPanel to provide custom drawing and animation features.
 */
public class AnimatedSierpinskiTriangleThree extends JPanel {
	public static final int MAX_DEPTH = 6; // Maximum depth of triangle recursion
	public static final int MAX_TRIANGLES = 8645; // Maximum number of triangles to be generated
	public static final Color[] COLOR_MAP = {Color.BLUE, Color.RED, Color.GREEN, Color.WHITE, Color.YELLOW, Color.MAGENTA, Color.ORANGE};
	private static final int DELAY = 100; // Delay for the animation timer in milliseconds
	private static final int WIDTH = 1000; // Width of the panel
	private static final int HEIGHT = 1000; // Height of the panel
	private static final int FONT_SIZE = 22; // Font size for the triangle count label
	final List<Point[]> triangles = new ArrayList<>(); // List of triangles represented by points
	int triangleCount = 1; // Initial triangle count
	final JLabel countLabel = new JLabel("Triangles: 1");

	/**
	 * Main method to create the JFrame and initialize the animation.
	 * @param args Command line arguments (not used).
	 */
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

	/**
	 * Constructor for AnimatedSierpinskiTriangleThree.
	 * Sets up the panel, label, and layout.
	 */
	public AnimatedSierpinskiTriangleThree() {
		countLabel.setFont(new Font(countLabel.getFont().getName(), Font.PLAIN, FONT_SIZE));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new BorderLayout());
		add(countLabel, BorderLayout.NORTH);
	}

	/**
	 * Generates the initial triangle and starts the animation timer to divide triangles recursively.
	 */
	public void generateTriangles() {
		Point[] points = getInitialTriangle(WIDTH, HEIGHT);
		triangles.add(points);
		Timer timer = new Timer(DELAY, e -> divideTriangle(points, 0, (Timer) e.getSource()));
		timer.start();
	}

	/**
	 * Paints the triangles onto the panel.
	 * @param g Graphics object used for drawing.
	 */
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

	/**
	 * Divides a triangle into three smaller triangles and schedules further divisions if within depth limits.
	 * @param points The vertices of the triangle to divide.
	 * @param depth The current depth of recursion.
	 * @param timer The timer controlling the animation.
	 */
	void divideTriangle(Point[] points, int depth, Timer timer) {
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

	/**
	 * Generates the initial triangle for the Sierpinski triangle.
	 * @param width The width of the panel.
	 * @param height The height of the panel.
	 * @return An array of Points representing the vertices of the initial triangle.
	 */
	public static Point[] getInitialTriangle(int width, int height) {
		return new Point[]{
				new Point(width / 2, 0),
				new Point(0, height),
				new Point(width, height)
		};
	}

	/**
	 * Calculates the midpoint between two given points.
	 * @param p1 The first point.
	 * @param p2 The second point.
	 * @return A Point representing the midpoint of the two points.
	 */
	public static Point midpoint(Point p1, Point p2) {
		int x = (p1.x + p2.x) / 2;
		int y = (p1.y + p2.y) / 2;
		return new Point(x, y);
	}

}