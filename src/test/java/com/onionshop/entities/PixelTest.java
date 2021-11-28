package com.onionshop.entities;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class PixelTest {
    Pixel pixel = new Pixel(new int[]{100, 100, 100});

    @Test
    void getRGB() {
        assert (Arrays.equals(new int[]{100, 100, 100, 255}, pixel.getRGB()));
    }

    @Test
    void setRGBError() {
        try {
            pixel.setRGB(new int[]{100, 100});
            assert false;
        } catch (Exception e) {
            if (e instanceof IndexOutOfBoundsException) {
                assert true;
            } else {
                assert false;
            }
        }
    }
}