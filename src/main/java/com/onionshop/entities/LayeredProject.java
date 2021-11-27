package com.onionshop.entities;
import com.onionshop.entities.Project;

import java.util.ArrayList;

public class LayeredProject {

    private String path;
    private int width;
    private int height;

    private ColourPalette colourPalette;

    // TODO: This is array is really an array of layers, will need to update the based project class

    public Project[] layers;

    /**
     * Creates an instance of a LayeredProject. This is a collection of projects.
     *
     * @param path location of .onion file.
     * @param width width of all layers.
     * @param height height of all layers.
     */
    public LayeredProject(String path, int width, int height) {
        this.path = path;
        this.colourPalette = new ColourPalette(new ArrayList<Colour>());

        this.layers = new Project[10];

        for (int i = 0; i <= 9; i ++) {
            layers[i] = new Project(path, width, height);
        }
    }

    /**
     * Creates an instance of a LayeredProject with a preset background colour. This is a collection of projects.
     *
     * @param path location of .onion file.
     * @param width width of all layers.
     * @param height height of all layers.
     * @param backgroundRGB preset background colour.
     */
    public LayeredProject(String path, int width, int height, int[] backgroundRGB) {
        this.path = path;
        this.colourPalette = new ColourPalette(new ArrayList<Colour>());

        this.layers = new Project[10];

        for (int i = 0; i <= 9; i ++) {
            layers[i] = new Project(path, width, height, backgroundRGB);
        }
    }

    /**
     * Gets path of the LayeredProject
     *
     * @return returns the path of the current LayeredProject .onion file.
     */
    public String getPath() {
        return path;
    }

    /**
     * Gets this LayeredProject's width.
     *
     * @return this LayeredProject's width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets this LayeredProject's height.
     *
     * @return this LayeredProject's height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the pixel of a given layer in the LayeredProject.
     *
     * @return a pixel of the specified coordinates and layer.
     */
    public Pixel getPixelByCoord(int x, int y, int layer) {
        return this.layers[layer].getPixelByCoord(x, y);
    }

    /**
     * Gets the name of the LayeredProject.
     *
     * @return the name of the LayeredProject
     */
    public String extractProjectName() {
        return path.substring(path.lastIndexOf("\\") + 1, path.indexOf(".onion"));
    }

    /**
     * Gets the colour palette of the LayeredProject.
     *
     * @return the ColourPalette of the Project
     */
    public ColourPalette getColourPalette() {
        return this.colourPalette;
    }

    /**
     * Sets the colour palette of this LayeredProject to the given colour palette. Updates all sub-project's colour
     * palettes.
     *
     * @param newColourPalette the new ColourPalette
     */

    public void setColourPalette(ColourPalette newColourPalette) {
        this.colourPalette = newColourPalette;
        for (int i = 0; i <= 9; i++) {
            this.layers[i].setColourPalette(newColourPalette);
        }
    }

    /**
     * Resets the canvas of a specified layer to a previous state. Used by the undo-redo manager.
     *
     * @param newDrawingCanvas the drawing canvas to update with
     * @param layer the layer of the canvas to update
     */

    public void setDrawingCanvas(Pixel[][] newDrawingCanvas, int layer) {
        this.layers[layer].setDrawingCanvas(newDrawingCanvas);
    }

}
