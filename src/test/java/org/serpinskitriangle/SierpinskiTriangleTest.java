package org.serpinskitriangle;

import org.junit.jupiter.api.Test;
import org.serpinskitriangleold.SierpinskiTriangle;

import static org.junit.jupiter.api.Assertions.*;

class SierpinskiTriangleTest {

    @Test
    void testMain() {
        assertDoesNotThrow(() -> {
            SierpinskiTriangle.main(new String[]{});
        });
    }

}
