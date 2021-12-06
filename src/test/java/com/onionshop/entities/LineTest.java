package com.onionshop.entities;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class LineTest {

    /**
     * This class is a junit test class that test specifically the Shape.
     */

    Line line;
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

        line = new Line(1);
        colour = new Colour("red", color);

        line.setStartingCoordinate(startingCoordinate);
        line.setEndingCoordinate(endingCoordinate);

    }

    /**
     * This test tests the calculateSlope method which calculates the slop of the line.
     */
    @Test
    public void calculateSlopeTest() {
        double expected = (double) 78 / 39;
        int[] distances = line.calculateStartEndDistance();

        double actual = line.calculateSlope(distances);
        assertEquals(expected, actual);
    }

    /**
     * This test tests the lineCalculationFormula method which calculates rge result of the equation of the line
     * by both the y or x value per inputted coordinate value depends on what is needed.
     */
    @Test
    public void lineCalculationFormulaTest() {
        int coordinateValue = 20;
        double m = (double) 78 / 39;
        double[] expected = new double[] { coordinateValue * m, coordinateValue / m};
        int[] distances = line.calculateStartEndDistance();

        double[] actual = line.lineCalculationFormula(coordinateValue, distances);

        assertArrayEquals(expected, actual);
    }

    /**
     * This test tests the calculateEffectedPixels method when draw stage is not 3.
     */
    @Test
    public void calculateEffectedPixelsDrawStageOtherTest() {
        line.drawStage = 0;

        int[][] expected = new int[][] {};

        line.calculateEffectedPixels();

        int[][] actual = line.pixelsEffectedByShape;

        assertArrayEquals(expected, actual);
    }

    /**
     * This test tests the calculateEffectedPixels method when draw stage is 3.
     */
    @Test
    public void calculateEffectedPixelsDrawStageThreeTest() {
        line.drawStage = 3;

        int[] startingCoordinate = {1, 1};
        int[] endingCoordinate = {6, 11};

        // slope = 10 / 5 = 2.0000000

        line.setStartingCoordinate(startingCoordinate);
        line.setEndingCoordinate(endingCoordinate);

        int[][] expected = new int[][] {
                {1, 1},{2, 3},{3, 5},{4, 7},{5, 9},{6, 11},
                {1, 1},{1, 2},{2, 3},{2, 4},{3, 5},{3, 6},{4, 7},{4, 8},{5, 9},{5, 10},{6, 11}
        };

        line.calculateEffectedPixels();

        int[][] actual = line.pixelsEffectedByShape;

        assertArrayEquals(expected, actual);
    }
}
