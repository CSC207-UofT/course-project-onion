/*
Used to store all project aspects that need to be saved between opening and closing.
This is a storage class, all elements that are made public are intended to be edited by a manager during runtime.
@author Finn Williams
 */
package com.onionshop.entities;

import com.onionshop.managers.LayerManager;
import com.onionshop.managers.OnionFileLoader;
import com.onionshop.managers.ProjectManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Project {
    // Drawing width in pixels
    final int width;
    // Drawing height in pixels
    final int height;
    // represents the project path and name, e.g. users/finn/drawings/drawing1.onion -- drawing1 is the name
    private String path;

    public List<Layer> layers;

    private Layer currLayer;

    // An array that holds default and user created colours
    private ColourPalette colourPalette;

    public ProjectManager projectManager = ProjectManager.getInstance();



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
        this.layers = new ArrayList<>();


        this.colourPalette = new ColourPalette(new ArrayList<Colour>());
        // create a new layer in layers
        // set layer.layerCanvas to this
        this.currLayer = new Layer(this.width, this.height, new int[]{255, 255, 255, 255});
        this.layers.add(this.currLayer);
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
        this.layers = new ArrayList<>();

        this.colourPalette = new ColourPalette(new ArrayList<Colour>());
        this.currLayer = new Layer(this.width, this.height, backgroundRGB);
        this.layers.add(currLayer);


    }

    /**
     * Gets path of project
     *
     * @return returns the path of the current project .onion file
     */
    public String getPath() {
        return path;
    }

    public List<Layer> getLayers(){ return this.layers;}

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
     * Returns this project's canvas width
     *
     * @return this project's width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns this project's canvas height
     *
     * @return this project's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the pixel located at the given x-y coordinates of this projects current layer
     *
     * @return the pixel located at the given x-y coordinates
     */
    public Pixel getPixelByCoord(int x, int y) {
        return this.currLayer.layerCanvas[x][y];

    }

    /**
     * Serializes this Project instance to .onion file format
     *
     * @return serialization returns serialized Project in onion format
     */
    public String[] serialize() {
        int numberOfLines = this.width * this.height * this.layers.size() + colourPalette.size() + 6 +
                this.layers.size();
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
        for (Colour colour : colourPalette.getColours()) {
            serialization[lineNumber] = colour.getName() + ":" +
                    String.valueOf(colour.RGB[0]) + "," +
                    String.valueOf(colour.RGB[1]) + "," +
                    String.valueOf(colour.RGB[2]) + "," +
                    String.valueOf(colour.RGB[3]);
            lineNumber++;
        }

        serialization[lineNumber] = "[pixels]";
        lineNumber++;

        // Saving layers
        for (int i=0; i < layers.size(); i++) {
            Layer l = layers.get(i);
            serialization[lineNumber] = "[layer:" + i + "]";
            lineNumber++;
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    serialization[lineNumber] = l.layerCanvas[x][y].RGB[0] + "," +
                            l.layerCanvas[x][y].RGB[1] + "," +
                            l.layerCanvas[x][y].RGB[2] + "," +
                            l.layerCanvas[x][y].RGB[3];
                    lineNumber++;
                }
            }
        }


        serialization[lineNumber] = "[end]"; //TODO: this might be causing problems

        return serialization;
    }

    /**
     * Extracts the project name from the directory.
     *
     * @return the project name.
     */
    public String extractProjectName() {
        return path.substring(path.lastIndexOf("\\") + 1, path.indexOf(".onion"));
    }

    /**
     * Returns this projects colour palette
     *
     * @return this projects ColourPalette.
     */
    public ColourPalette getColourPalette() {
        return this.colourPalette;
    }

    /**
     * Sets the colour palette of this project to the given colour palette
     *
     * @param newColourPalette the new ColourPalette
     */
    public void setColourPalette(ColourPalette newColourPalette) {
        this.colourPalette = newColourPalette;
    }

    public void setDrawingCanvas(Pixel[][] newDrawingCanvas) { this.currLayer.setLayerCanvas(newDrawingCanvas); }

    public void setLayers(List<Layer> newLayers) { this.layers = newLayers; }

    /**
     * Return the current pixel array of this project.
     * @return the current pixel array of this project.
     */
    public Pixel[][] getPixelArray() {
        Pixel[][] pixelArray = new Pixel[projectManager.getCurrentProject().getWidth()]
                [projectManager.getCurrentProject().getHeight()];
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                int[] rgbValues = projectManager.getCurrentProject().getPixelByCoord(x, y).getRGB();
                Pixel pixel = new Pixel(rgbValues);
                pixelArray[x][y] = pixel;
            }
        }
        return pixelArray;
    }
}