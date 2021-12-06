package com.onionshop.entities;

public class Layer {
    public Pixel[][] layerCanvas;

    public Layer(int width, int height, int[] LayerRGB) {
        this.layerCanvas = new Pixel[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                layerCanvas[x][y] = new Pixel(LayerRGB); //Transparent by default
            }
        }
    }

    public void setLayerCanvas(Pixel[][] newDrawingCanvas) {
        this.layerCanvas = newDrawingCanvas;
    }

    public Pixel getPixelByCoord(int x, int y) {
        return this.layerCanvas[x][y];
    }
}