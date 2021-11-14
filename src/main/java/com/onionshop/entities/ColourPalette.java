package com.onionshop.entities;

import java.util.ArrayList;
import java.util.Objects;

public class ColourPalette {

    // An array that holds default and user created colours
    public ArrayList<Colour> colours;

    /**
     * Creates instance of ColourPalette
     *
     * @param colours: initial list of colours for the colour palette
     */
    public ColourPalette(ArrayList<Colour> colours) {
        this.colours = colours;
    }

    /**
     * Adds a new custom colour to the colour palette
     *
     * @param newColour the new Colour instance to be added
     */
    public void addColour(Colour newColour) {
        this.colours.add(newColour);
    }

    /**
     * Removes the given Colour instance from the colourPalette
     *
     * @param colour Colour instance to be removed
     */
    public void removeColour(Colour colour) {
        colours.remove(colour);
    }

    /**
     * Removes the colour of the given name from colourPalette
     *
     * @param colourName the name of the colour to be removed
     */
    public void removeColour(String colourName) {
        for (Colour c : colours) {
            if (Objects.equals(c.getName(), colourName)) {
                colours.remove(c);
                return;
            }
        }
    }

    /**
     * Removes the colour in the given index from colourPalette
     *
     * @param index the index of the colour to be removed
     */
    public void removeColour(int index) {
        this.colours.remove(index);
    }

    /**
     * Returns the number of colours in the colour palette
     *
     * @return the number of colours in the colour palette
     */
    public int size() {
        return colours.size();
    }

    /**
     * Returns the colours in this colour palette
     *
     * @return the colours in this colour palette
     */
    public ArrayList<Colour> getColours() {
        return this.colours;
    }

    /**
     * Returns the colour at the given index in the colour palette
     *
     * @return the colour at the given index in the colour palette
     */
    public Colour getColour(int index) {
        return colours.get(index);
    }

    /**
     * Gets the first Colour instance with a given name
     *
     * @param colourName name of colour to be returned
     * @return returns Colour class of the given name, or null if the name is not found
     */
    public Colour getColour(String colourName) {
        for (Colour c : colours) {
            if (Objects.equals(c.getName(), colourName)) {
                return c;
            }
        }
        return null;
    }

}
