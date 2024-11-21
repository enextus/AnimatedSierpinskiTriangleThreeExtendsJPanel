Feature: TriangleManager Functionality
  Scenario: Generating the initial triangle
    Given a TriangleManager with a maximum depth of 7 and maximum triangles of 13999
    When I generate the initial triangle for a panel of width 800 and height 800
    Then the initial triangle should have vertices (400, 0), (0, 800), and (800, 800)

  Scenario: Calculating the midpoint
    Given two points (0, 0) and (10, 10)
    When I calculate the midpoint
    Then the midpoint should be (5, 5)

  Scenario: Dividing a triangle
    Given a triangle with vertices (0, 0), (4, 0), and (2, 4)
    When I divide the triangle
    Then three smaller triangles should be generated
