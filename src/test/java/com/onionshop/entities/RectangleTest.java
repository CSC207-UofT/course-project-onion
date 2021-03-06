package com.onionshop.entities;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RectangleTest {

    /**
     * This class is a junit test class that test specifically the Shape.
     */

    Rectangle rectangle;
    Colour colour;

    /**
     * The Test set up for before each test, the current tool is Line for now.
     *
     * @throws Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        int[] color = {255, 255, 255};
        int[] startingCoordinate = {1, 2};
        int[] endingCoordinate = {40, 80};

        rectangle = new Rectangle();
        colour = new Colour("red", color);

        rectangle.setStartingCoordinate(startingCoordinate);
        rectangle.setEndingCoordinate(endingCoordinate);

    }

    /**
     * This test tests the calculateEffectedPixels method when draw stage is 0.
     */
    @Test
    public void calculateEffectedPixelsTestDrawStageOtherTest() {
        rectangle.drawStage = 0;

        int[][] expected = new int[][] {};

        rectangle.calculateEffectedPixels();

        int[][] actual = rectangle.pixelsEffectedByShape;

        assertArrayEquals(expected, actual);
    }

    /**
     * This test tests the calculateEffectedPixels method when draw stage is 2.
     */
    @Test
    public void calculateEffectedPixelsTestDrawStageThreeTest() {
        rectangle.drawStage = 3;

        int[] startingCoordinate = {1, 1};
        int[] endingCoordinate = {4, 5};

        rectangle.setStartingCoordinate(startingCoordinate);
        rectangle.setEndingCoordinate(endingCoordinate);

        int[][] expected = new int[][] {
                {1, 1},{2, 1},{3, 1},
                {1, 2},{2, 2},{3, 2},
                {1, 3},{2, 3},{3, 3},
                {1, 4},{2, 4},{3, 4},
        };

        rectangle.calculateEffectedPixels();

        int[][] actual = rectangle.pixelsEffectedByShape;

        assertArrayEquals(expected, actual);
    }
}
