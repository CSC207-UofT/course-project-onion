/*
Used to store all project aspects that need to be saved between opening and closing.
This is a storage class, all elements that are made public are intended to be edited by a manager during runtime.

@author Finn Williams
 */
package com.onionshop.entities;

import com.onionshop.managers.OnionFileLoader;

import java.util.ArrayList;
import java.util.Objects;

public class Project {
    // Drawing width in pixels
    final int width;
    // Drawing height in pixels
    final int height;
    // represents the project path and name, e.g. users/finn/drawings/drawing1.onion -- drawing1 is the name
    private String path;

    // An array that holds default and user created colours
    public ArrayList<Colour> colourPalette;

    //2d array representing each pixel of the drawing canvas with Pixel
    public Pixel[][] drawingCanvas;

    /**
     * Creates instance of project
     *
     * @param path:   location of .onion project file
     * @param width:  width of drawing
     * @param height: height of drawing
     */
    public Project(String path, int width, int height) {
        this.path = path;
        this.width = width;
        this.height = height;

        this.colourPalette = new ArrayList<Colour>();

        this.colourPalette.add(new Colour("black", new int[]{0, 0, 0})); //default pen colour

        this.drawingCanvas = new Pixel[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                drawingCanvas[x][y] = new Pixel(new int[]{255, 255, 255}); //White by default
            }
        }

    }

    /**
     * Creates instance of project with a given background colour
     *
     * @param path:          location of .onion project file
     * @param width:         width of drawing
     * @param height:        height of drawing
     * @param backgroundRGB: colour of drawing canvas background
     */
    public Project(String path, int width, int height, int[] backgroundRGB) {
        this.path = path;
        this.width = width;
        this.height = height;

        colourPalette = new ArrayList<Colour>();
        this.colourPalette.add(new Colour("black", new int[]{0, 0, 0})); //default pen colour

        this.drawingCanvas = new Pixel[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                drawingCanvas[x][y] = new Pixel(backgroundRGB);
            }
        }

    }

    /**
     * Gets path of project
     *
     * @return returns the path of the current project .onion file
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets new path
     *
     * @param newPath the new path to which this.path will be set
     */
    public void updatePath(String newPath) throws Exception {
        if (OnionFileLoader.isDirectoryValid(newPath)) {
            path = newPath;
        } else {
            throw new Exception("Invalid path was given: " + newPath);
        }

    }

    /**
     * Adds a new custom colour to the colour palette
     *
     * @param newColour the new Colour instance to be added
     */
    public void addColour(Colour newColour) {
        this.colourPalette.add(newColour);
    }

    /**
     * Removes the given Colour instance from the colourPalette
     * @param colour Colour instance to be removed
     */
    public void removeColour(Colour colour) {
        colourPalette.remove(colour);
    }

    /**
     * Removes the colour of the given name from colourPalette
     * @param colourName the name of the colour to be removed
     */
    public void removeColour(String colourName) {
        for (Colour c : colourPalette) {
            if (Objects.equals(c.name, colourName)) {
                colourPalette.remove(c);
                return;
            }
        }
    }

    /**
     * Returns this project's canvas width
     @return this project's width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns this project's canvas height
     @return this project's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the pixel located at the given x-y coordinates of this projects drawing canvas
     @return the pixel located at the given x-y coordinates
     */
    public Pixel getPixelByCoord(int x, int y) {
        return this.drawingCanvas[x][y];
    }

    /**
     * Serializes this Project instance to .onion file format
     *
     * @return serialization returns serialized Project in onion format
     */
    public String[] serialize() {
        int numberOfLines = this.width * this.height + colourPalette.size() + 6;
        int lineNumber = 0;
        String[] serialization = new String[numberOfLines];

        //FORMATTING

        //adding tags and constants
        serialization[0] = "[dimensions]";
        serialization[1] = "width:" + String.valueOf(width);
        serialization[2] = "height:" + String.valueOf(height);
        serialization[3] = "[saved colours]";
        lineNumber += 4;

        //adding saved colours -> <name>:R,G,B
        for (Colour colour : colourPalette) {
            serialization[lineNumber] = colour.name + ":" +
                    String.valueOf(colour.RGB[0]) + "," +
                    String.valueOf(colour.RGB[1]) + "," +
                    String.valueOf(colour.RGB[2]);
            lineNumber++;
        }

        serialization[lineNumber] = "[pixels]";
        lineNumber++;

        // Adding Pixel RGB values -> R,G,B
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                serialization[lineNumber] = String.valueOf(drawingCanvas[x][y].RGB[0]) + "," +
                        String.valueOf(drawingCanvas[x][y].RGB[1]) + "," +
                        String.valueOf(drawingCanvas[x][y].RGB[2]);
                lineNumber++;
            }
        }
        serialization[lineNumber] = "[end]";

        return serialization;
    }

    /**
     * Extracts the project name from the directory.
     * @return the project name.
     */
    public String extractProjectName() {
        String projectName = this.path.substring(this.path.lastIndexOf("\\")+1);
        return projectName;
    }
}
