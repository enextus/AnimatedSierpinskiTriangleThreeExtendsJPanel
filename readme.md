# Animated Sierpinski Triangle

This project generates and animates a **Sierpinski Triangle**, a famous fractal, using Java and Swing. It visualizes the recursive process of subdividing a triangle into smaller triangles, with a colorful, animated representation.

## Features
- **Recursive Sierpinski Triangle Generation**: The fractal is generated up to a specified depth, with a maximum of 8645 triangles.
- **Animation**: The process of dividing triangles is animated, allowing viewers to see how the fractal emerges over time.
- **Customizable Settings**: The depth of recursion, delay for animation, and other parameters can be easily adjusted.

## Getting Started

### Prerequisites
- **Java 21** or later
- **Maven** (for dependency management)

### Installation
1. **Clone the Repository**:
   ```sh
   git clone https://github.com/yourusername/AnimatedSierpinskiTriangle.git
   cd AnimatedSierpinskiTriangle
   ```

2. **Build the Project**:
   Run the following command to build the project using Maven:
   ```sh
   mvn clean install
   ```

3. **Run the Application**:
   You can run the application with:
   ```sh
   mvn exec:java -Dexec.mainClass="org.serpinskitriangle.AnimatedSierpinskiTriangleThree"
   ```

### Running Tests
This project includes unit tests written with **JUnit** and **Mockito**. To run the tests:
```sh
mvn test
```

## Project Structure
- **src/main/java/org/serpinskitriangle**: Contains the main source code.
- **src/test/java/org/serpinskitriangle**: Contains unit tests for the project.

## Usage
After starting the application, you will see an animated display of a Sierpinski Triangle being recursively generated. The panel includes a label showing the current number of triangles.

You can modify the following parameters in `AnimatedSierpinskiTriangleThree`:
- **MAX_DEPTH**: The maximum depth of recursion.
- **MAX_TRIANGLES**: The maximum number of triangles to be generated.
- **DELAY**: The delay in milliseconds for the animation timer.

## Dependencies
- **JUnit 5**: For unit testing.
- **Mockito**: For mocking in unit tests.
- **Swing**: For GUI rendering.
- **Cucumber**: Used for behavior-driven testing.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributing
Contributions are welcome! Please feel free to submit a pull request or open an issue to discuss changes.

## Contact
If you have questions or feedback, feel free to reach out:
- **Email**: enextus@gmail.com

Enjoy exploring fractals and animations! 😊

