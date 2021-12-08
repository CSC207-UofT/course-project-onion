package com.onionshop.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BrushTest {

    Brush brush;

    @BeforeEach
    public void setUp() {
        brush = new Pen("round", 1);
    }

    @Test
    public void testCalculateEffectedPixels() {
        int[][] expected = new int[][] {
                {-1, -1}, {-1, 0}, {-1, 1}, {0, -1},
                {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
        };

        brush.calculateEffectedPixels();
        assertEquals(expected.length, brush.getPixelsEffectedByBrush().length);
        assertArrayEquals(brush.getPixelsEffectedByBrush(), expected);
    }

    @Test
    public void testDraw() {
        Project project = new Project("path", 15, 15);
        Colour colour = new Colour("red", new int[]{255, 0, 0});
        int[][] expected = new int[][] {
                {6, 7}, {6, 8}, {6, 9}, {7, 7}, {7, 8},
                {7, 9}, {8, 7}, {8, 8}, {8, 9}
        };
        int[][] actual = brush.draw(project, colour, 7, 8, new Layer(10, 10, new int[]{0, 0, 0, 0}));

        assertArrayEquals(expected, actual);
    }
}
