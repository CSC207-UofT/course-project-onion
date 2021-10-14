/*
Used to hold a pixel's RGB value

@author Finn Williams
 */
package com.onionshop;

class Pixel {
    // The RGB values of the pixel, {red, green, blue}
    protected int[] RGB = new int[3];

    /*
    Creates Pixel instance

    @param RGB the RGB value of the pixel
     */
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
