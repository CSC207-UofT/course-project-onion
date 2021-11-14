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
    public void calculateEffectedPixelsTestDrawStageZero() {
        rectangle.drawStage = 0;

        int[][] expected = new int[][] {
                {-2, -2},{-2, -1},{-2, 0},{-2, 1},{-2, 2},
                {-1, -2},{-1, -1},{-1, 0},{-1, 1},{-1, 2},
                {0, -2},{0, -1},{0, 0},{0, 1},{0, 2},
                {1, -2},{1, -1},{1, 0},{1, 1},{1, 2},
                {2, -2},{2, -1},{2, 0},{2, 1},{2, 2},
        };

        rectangle.calculateEffectedPixels();

        int[][] actual = rectangle.pixelsEffectedByShape;

        assertArrayEquals(expected, actual);
    }

    /**
     * This test tests the calculateEffectedPixels method when draw stage is 2.
     */
    @Test
    public void calculateEffectedPixelsTestDrawStageTwo() {
        rectangle.drawStage = 2;

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

    /**
     * This test tests the draw() method for the entire drawing process.
     */
    @Test
    public void testDrawStage() {

        rectangle.drawStage = 0;
        rectangle.calculateEffectedPixels();

        assertEquals(1, rectangle.drawStage);

        Project project = new Project("path", 30, 30);
        Colour colour = new Colour("red", new int[]{255, 0, 0});

        int[][] expected1 = new int[][] {
                {0, 0},{0, 0},{0, 0},{0, 0},{0, 0},
                {0, 0},{0, 0},{0, 0},{0, 0},{0, 0},
                {0, 0},{0, 0},{1, 1},{1, 2},{1, 3},
                {0, 0},{0, 0},{2, 1},{2, 2},{2, 3},
                {0, 0},{0, 0},{3, 1},{3, 2},{3, 3},
        };
        int[][] actual1 = rectangle.draw(project, colour, 1, 1);

        assertArrayEquals(expected1, actual1);
        assertEquals(2, rectangle.drawStage);

        int[][] expected2 = new int[][] {
                {1, 1},{2, 1},{3, 1},
                {1, 2},{2, 2},{3, 2},
                {1, 3},{2, 3},{3, 3},
                {1, 4},{2, 4},{3, 4},
        };

        int[][] actual2 = rectangle.draw(project, colour, 4, 5);

        assertArrayEquals(expected2, actual2);
    }
}
