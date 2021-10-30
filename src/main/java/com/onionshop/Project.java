/*
Used to store all project aspects that need to be saved between opening and closing.
This is a storage class, all elements that are made public are intended to be edited by a manager during runtime.

@author Finn Williams
 */
package com.onionshop;

public class Project {
    // Drawing width in pixels
    final int width;
    // Drawing height in pixels
    final int height;
    // represents the project path and name, e.g. users/finn/drawings/drawing1.onion -- drawing1 is the name
    private String path;

    // An array that holds default and user created colours
    Colour[] colourPalette = {new Colour("black", new int[]{0, 0, 0})};
    //TODO: implement later, right now we just have one default colour

    //2d array representing each pixel of the drawing canvas with Pixel
    Pixel[][] drawingCanvas;

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

        this.drawingCanvas = new Pixel[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                drawingCanvas[x][y] = new Pixel(backgroundRGB); //White by default
            }
        }

    }

    /**
     * Gets path of project
     *
     * @return path: the path of the current project .onion file
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets new path
     *
     * @param newPath: the new path to which this.path will be set
     */
    public void updatePath(String newPath) {
        path = newPath;
        //TODO (optional): add a checker to see if the path is valid and writable
    }

    public void addColour(Colour newColour) {
        Colour[] newCP = new Colour[colourPalette.length + 1];
        for (int i = 0; i < colourPalette.length; i++) {
            newCP[i] = colourPalette[i];
        }
        newCP[newCP.length - 1] = newColour;

        this.colourPalette = newCP;
    }


    /**
     * Serializes this Project instance to .onion file format
     *
     * @return: returns serialized Project in onion format
     */
    public String[] Serialize() {
        int numberOfLines = this.width * this.height + colourPalette.length + 6;
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
}
