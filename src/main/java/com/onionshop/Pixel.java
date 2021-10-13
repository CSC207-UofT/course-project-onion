package com.onionshop;

public class Pixel {
    private int[] RGB = new int[3];

    public Pixel(int[] RGB) {
        this.RGB = RGB;
    }

    public int[] getRGB() {
        return RGB;
    }

    public void setRGB(int[] newRGB) {
        this.RGB = newRGB;
    }
}
