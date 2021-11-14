/*
Used to hold a pixel's RGB value

@author Finn Williams
 */
package com.onionshop.entities;

public class Pixel {
    // The RGB values of the pixel, {red, green, blue}
    protected int[] RGB = new int[3];

    /**
     * Creates Pixel instance
     *
     * @param RGB the RGB value of the pixel
     */
    public Pixel(int[] RGB) {
        setRGB(RGB);
    }

    public int[] getRGB() {
        return RGB;
    }

    public void setRGB(int[] newRGB) throws IndexOutOfBoundsException {
        if (newRGB.length == 3) {
            this.RGB = newRGB;
        } else {
            throw new IndexOutOfBoundsException("RGB array contains exactly 3 values, " +
                    newRGB.length +
                    " were provided");
        }
    }
}
