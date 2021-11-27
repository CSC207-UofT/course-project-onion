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

    public LayeredProject(String path, int width, int height) {
        this.path = path;
        this.colourPalette = new ColourPalette(new ArrayList<Colour>());

        this.layers = new Project[10];

        for (int i = 0; i <= 9; i ++) {
            layers[i] = new Project(path, width, height);
        }
    }

    public LayeredProject(String path, int width, int height, int[] backgroundRGB) {
        this.path = path;
        this.colourPalette = new ColourPalette(new ArrayList<Colour>());

        this.layers = new Project[10];

        for (int i = 0; i <= 9; i ++) {
            layers[i] = new Project(path, width, height, backgroundRGB);
        }
    }

    public String getPath() {
        return path;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Pixel getPixelByCoord(int x, int y, int layer) {
        return this.layers[layer].getPixelByCoord(x, y);
    }

    public String extractProjectName() {
        return path.substring(path.lastIndexOf("\\") + 1, path.indexOf(".onion"));
    }

    public ColourPalette getColourPalette() {
        return this.colourPalette;
    }

    public void setColourPalette(ColourPalette newColourPalette) {
        this.colourPalette = newColourPalette;
        for (int i = 0; i <= 9; i++) {
            this.layers[i].setColourPalette(newColourPalette);
        }
    }

    public void setDrawingCanvas(Pixel[][] newDrawingCanvas, int layer) {
        this.layers[layer].setDrawingCanvas(newDrawingCanvas);
    }

}
