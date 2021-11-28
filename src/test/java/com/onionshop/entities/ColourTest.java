package com.onionshop.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class ColourTest {

    Colour colour;

    @BeforeEach
    public void setUp() {
        colour = new Colour("red", new int[] {255, 0, 0});
    }

    @Test
    public void testSetRGB1() {
        int[] newRGB = new int[] {240, 0, 0, 255};
        colour.setRGB(newRGB);
        assertArrayEquals(newRGB, colour.getRGB());
    }

    @Test
    public void testSetRGB2() {
        int[] newRGB = new int[] {270, 0, 300};
        int[] expectedRGB = new int[] {255, 0, 255, 255};
        colour.setRGB(newRGB);
        assertArrayEquals(expectedRGB, colour.getRGB());
    }

}
