package com.onionshop.entities;

public class Layer {
    public Pixel[][] layerCanvas;

    /**
     * Creates an instance of a layer with the given width and height
     * @param width the width of the layer
     * @param height the height of the layer
     * @param layerRGB the colour of the layer (transparent by default)
     */
    public Layer(int width, int height, int[] layerRGB) {
        this.layerCanvas = new Pixel[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                layerCanvas[x][y] = new Pixel(layerRGB); //Transparent by default
            }
        }
    }

    /**
     * Sets the pixel array of this layer to the given new pixel array
     * @param newDrawingCanvas the new pixel array
     */
    public void setLayerCanvas(Pixel[][] newDrawingCanvas) {
        this.layerCanvas = newDrawingCanvas;
    }

    /**
     * Returns the pixel array of this layer
     * @return the pixel array of this layer
     */
    public Pixel getPixelByCoord(int x, int y) {
        return this.layerCanvas[x][y];
    }
}