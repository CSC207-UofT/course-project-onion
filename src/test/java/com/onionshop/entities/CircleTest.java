package com.onionshop.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CircleTest {

    /**
     * This class is a junit test class that test specifically the Shape.
     */

    Circle circle;
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

        circle = new Circle();
        colour = new Colour("red", color);

        circle.setStartingCoordinate(startingCoordinate);
        circle.setEndingCoordinate(endingCoordinate);
    }

    /**
     * This test tests the calculateEffectedPixels method when draw stage is 0.
     */
    @Test
    public void calculateEffectedPixelsTestDrawStageOtherTest() {
        circle.drawStage = 0;

        int[][] expected = new int[][] {};

        circle.calculateEffectedPixels();

        int[][] actual = circle.pixelsEffectedByShape;

        assertArrayEquals(expected, actual);
    }

    /**
     * This test tests the calculateEffectedPixels method when draw stage is 2.
     */
    @Test
    public void calculateEffectedPixelsTestDrawStageThreeTest() {
        circle.drawStage = 3;

        int[] startingCoordinate = {1, 1};
        int[] endingCoordinate = {4, 5};

        circle.setStartingCoordinate(startingCoordinate);
        circle.setEndingCoordinate(endingCoordinate);

        int[][] expected = new int[][] {
                {1, 1},{2, 1},{3, 1}, {4, 1}, {2, 4}, {3, 3}, {4, 1},
                {4, 2},{3, 3},{1, 4},{1, 2},{1, 3},{1, 4},
                {0, 0},{0, 0},{0, 0},{0, 0},{0, 0}
        };

        circle.calculateEffectedPixels();

        int[][] actual = circle.pixelsEffectedByShape;
        assertArrayEquals(expected, actual);
    }
}
