package com.onionshop.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class LayerTest {

    Layer layer;

    @BeforeEach
    public void setUp() {
        layer = new Layer(100, 100, new int[]{255, 255, 255, 255});
    }

    @Test
    public void testGetPixelByCoord() {
        int[] expected = {255, 255, 255, 255};

        Pixel actual = layer.getPixelByCoord(50, 50);
        assertArrayEquals(actual.getRGB(), expected);
    }

}
