/*
Used to store user defined/chosen colours and default colours for the colour palette

@author Finn Williams
 */

package com.onionshop.entities;

import com.onionshop.entities.Pixel;

public class Colour extends Pixel {
    //name of the colour, defined by user of default
    private final String name;

    /**
     * Creates instance of Colour
     *
     * @param name: see name instance variable
     * @param RGB:  RGB value of Colour
     */
    public Colour(String name, int[] RGB) {
        super(RGB);
        this.name = name;
    }

    /**
     * Returns this colour as a pixel
     *
     * @return this colours name
     */
    public Pixel getPixel() {
        return new Pixel(this.RGB);
    }

    /**
     * Returns the name of this colour
     *
     * @return this colours name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the RGB value of the colour
     *
     * @param newRGB the [red, green, blue] values for the colour
     */
    @Override
    public void setRGB(int[] newRGB) {
        for (int i = 0; i < 3; i++) {
            super.RGB[i] = newRGB[i];
            if (RGB[i] > 255) {
                RGB[i] = 255;
            } else if (RGB[i] < 0) {
                RGB[i] = 0;
            }
        }
    }

    /**
     * Safely modifies RGB values of Pixel instance
     *
     * @param changes: the changes of each RGB value (e.g. {10, 0, 0} would increase the red RGB value by 10)
     */
    public void modify(int[] changes) {
        for (int i = 0; i < 3; i++) {
            super.RGB[i] += changes[i];
            if (RGB[i] > 255) {
                RGB[i] = 255;
            } else if (RGB[i] < 0) {
                RGB[i] = 0;
            }
        }
    }
}
