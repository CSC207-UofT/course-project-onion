package com.onionshop.entities;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class ShapeTest {

    /**
     * This class is a junit test class that test specifically the Shape.
     */

    Shape shape;
    Colour colour;

    /**
     * The Test set up for before each test, the current tool is Line for now.
     *
     * @throws Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        int[] color = {255, 255, 255};
        int[] startingCoordinate = {32, 700};
        int[] endingCoordinate = {100, 430};

        shape = new Line(1);
        colour = new Colour("red", color);

        shape.setStartingCoordinate(startingCoordinate);
        shape.setEndingCoordinate(endingCoordinate);

    }

    /**
     * This test tests the method calculateStartEndDistance which calculates the distance between rise and run of the
     * start and end coordinates.
     */
    @Test
    public void calculateStartEndDistanceTest() {
        int[] expected = new int[] {-68, 270};
        int[] actual = shape.calculateStartEndDistance();

        assertArrayEquals(expected, actual);
    }
}
