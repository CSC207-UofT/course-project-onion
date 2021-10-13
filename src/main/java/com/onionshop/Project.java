package com.onionshop;

public class Project {
    /*
    TODO
     */
    final int width;
    final int height;
    Pixel backgroundColour = new Pixel(new int[]{255, 255, 255}); //default is white

    public Colour[] colourPalette; //TODO add later
    public Pixel[][] drawingCanvas;



    public Project(int width, int height) {
        this.width = width;
        this.height = height;

        this.drawingCanvas = new Pixel[width][height];
        for (int x=0; x < width; x++) {
            for (int y=0; y < height; y++) {
                drawingCanvas[x][y] = backgroundColour;
            }
        }

    }
}
