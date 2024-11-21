package org.serpinskitriangle;

import io.cucumber.java.en.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleManagerStepDefinitions {

    private TriangleManager manager;
    private Point[] initialTriangle;
    private Point midpoint;
    private int maxDepth = 7;
    private int maxTriangles = 13999;

    @Given("a TriangleManager with a maximum depth of {int} and maximum triangles of {int}")
    public void setupTriangleManager(int depth, int maxTriangles) {
        manager = new TriangleManager(depth, maxTriangles);
    }

    @When("I generate the initial triangle for a panel of width {int} and height {int}")
    public void generateInitialTriangle(int width, int height) {
        initialTriangle = manager.getInitialTriangle(width, height);
    }

    @Then("the initial triangle should have vertices \\({int}, {int}\\), \\({int}, {int}\\), and \\({int}, {int}\\)")
    public void verifyInitialTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        assertEquals(new Point(x1, y1), initialTriangle[0]);
        assertEquals(new Point(x2, y2), initialTriangle[1]);
        assertEquals(new Point(x3, y3), initialTriangle[2]);
    }

    @Given("two points \\({int}, {int}\\) and \\({int}, {int}\\)")
    public void setMidpointPoints(int x1, int y1, int x2, int y2) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        midpoint = TriangleManager.midpoint(p1, p2);
    }

    @When("I calculate the midpoint")
    public void calculateMidpoint() {
        // Midpoint is already calculated in the @Given step
    }

    @Then("the midpoint should be \\({int}, {int}\\)")
    public void verifyMidpoint(int x, int y) {
        assertEquals(new Point(x, y), midpoint);
    }

}
