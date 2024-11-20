package org.serpinskitriangle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * AnimatedSierpinskiTriangleThree class generates and animates a Sierpinski triangle.
 * Extends JPanel to provide custom drawing and animation features.
 */
public class AnimatedSierpinskiTriangleThree extends JPanel {

	public static final int MAX_DEPTH = 7; // Maximum recursion depth
	public static final int MAX_TRIANGLES = 13999; // Maximum triangle count
	public static final Color[] COLOR_MAP = {Color.BLUE, Color.RED, Color.GREEN, Color.WHITE, Color.YELLOW, Color.MAGENTA, Color.ORANGE};
	private static final int DELAY = 100; // Delay for animation in milliseconds
	private static final int WIDTH = 1000; // Panel width
	private static final int HEIGHT = 1000; // Panel height
	private static final int FONT_SIZE = 22; // Font size for the triangle count label

	private final List<Point[]> triangles = new ArrayList<>();
	private int triangleCount = 1;
	private final JLabel countLabel = new JLabel("Triangles: 1");
	private BufferedImage triangleImage; // Cache for rendered triangles
	private final TriangleManager triangleManager;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Animated Sierpinski Triangle Three");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(WIDTH, HEIGHT);
			AnimatedSierpinskiTriangleThree contentPane = new AnimatedSierpinskiTriangleThree();
			frame.setContentPane(contentPane);
			frame.setVisible(true);
			contentPane.startAnimation();
		});
	}

	/**
	 * Constructor initializes the panel, label, and triangle manager.
	 */
	public AnimatedSierpinskiTriangleThree() {
		triangleManager = new TriangleManager(MAX_DEPTH, MAX_TRIANGLES);
		countLabel.setFont(new Font(countLabel.getFont().getName(), Font.PLAIN, FONT_SIZE));
		setLayout(new BorderLayout());
		add(countLabel, BorderLayout.NORTH);
	}

	/**
	 * Starts the triangle generation and animation.
	 */
	public void startAnimation() {
		Point[] initialTriangle = triangleManager.getInitialTriangle(WIDTH, HEIGHT);
		triangles.add(initialTriangle);

		Timer animationTimer = new Timer(DELAY, e -> {
			List<Point[]> currentTriangles = new ArrayList<>(triangles);
			for (Point[] triangle : currentTriangles) {
				if (triangleManager.canDivide(triangleCount)) {
					List<Point[]> newTriangles = triangleManager.divideTriangle(triangle);
					triangles.addAll(newTriangles);
					triangleCount += newTriangles.size();
					countLabel.setText("Triangles: " + triangleCount);
					repaint();
				}
			}
		});
		animationTimer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (triangleImage == null) {
			triangleImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = triangleImage.createGraphics();
			drawTriangles(g2d);
			g2d.dispose();
		}
		g.drawImage(triangleImage, 0, 0, null);
	}

	private void drawTriangles(Graphics g) {
		for (int i = 0; i < triangles.size(); i++) {
			g.setColor(COLOR_MAP[i % COLOR_MAP.length]);
			int[] x = {(int) triangles.get(i)[0].getX(), (int) triangles.get(i)[1].getX(), (int) triangles.get(i)[2].getX()};
			int[] y = {(int) triangles.get(i)[0].getY(), (int) triangles.get(i)[1].getY(), (int) triangles.get(i)[2].getY()};
			g.fillPolygon(x, y, 3);
		}
	}
}
