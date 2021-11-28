/*
Used to hold a pixel's RGB value

@author Finn Williams
 */
package com.onionshop.entities;

public class Pixel {
    // The RGB and alpha values of the pixel, {red, green, blue, alpha}
    protected int[] RGB = new int[4];

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
        if (newRGB.length == 4) {
            this.RGB = newRGB;
        } else if (newRGB.length == 3) {
            this.RGB = new int[]{newRGB[0], newRGB[1], newRGB[2], 255}; //default: full opacity
        } else {
            throw new IndexOutOfBoundsException("RGB array contains exactly 4 values, " +
                    newRGB.length +
                    " were provided");
        }
    }
}
