package com.onionshop.entities;

import com.onionshop.managers.LayerManager;

import java.util.Arrays;

public abstract class Brush implements Tool {
    private String brushShape;
    private int brushSize;
    protected int[][] pixelsEffectedByBrush;

    /**
     * Construct a Brush that has the properties brush shape and brush size.
     *
     * @param brushShape    String of the shape of the brush, all lower case
     * @param brushSize     int of the size of the brush, represents pixel size
     */
    public Brush(String brushShape, int brushSize) {
        this.brushShape = brushShape;
        this.brushSize = brushSize;
    }

    /**
     * Returns the current SHAPE of the brush.
     *
     * @return      the current shape of the brush in String
     */
    public String getBrushShape() {
        return brushShape;
    }

    /**
     * Returns the current SIZE of the brush.
     *
     * @return      the current size of the brush in int
     */
    public int getBrushSize() {
        return brushSize;
    }

    /**
     * Sets the current SHAPE of the brush to an input string brushShape.
     *
     * @param brushShape    String of the shape of the brush, all lower case
     */
    public void setBrushShape(String brushShape) {
        this.brushShape = brushShape;
    }

    /**
     * Sets the current SIZE of the brush to an input string brushSize.
     *
     * @param brushSize     String of the size of the brush, represents pixel size
     */
    public void setBrushSize(int brushSize) {
        this.brushSize = brushSize;
        calculateEffectedPixels();
    }

    /**
     * Calculates and stores the desired pixel output stamp in calculateEffectedByPixels for each individual click
     * where draw() is initiated.
     *
     * The current calculateEffectedPixels outputs a square with dimensions <brushSize> * <brushSize>
     */
    public void calculateEffectedPixels() {
        pixelsEffectedByBrush = new int[(brushSize * 2 + 1) * (brushSize * 2 + 1)][2];

        int counter = 0;
        for (int x = -brushSize; x < brushSize + 1; x++) {
            for (int y = -brushSize; y < brushSize + 1; y++) {
                this.pixelsEffectedByBrush[counter][0] = x;
                this.pixelsEffectedByBrush[counter][1] = y;
                counter++;
            }
        }
    }

    /**
     * Returns and 2D integer array representing the pixel output stamp for each individual click when drawing
     * @return pixels effected by brush
     */
    public int[][] getPixelsEffectedByBrush() {
        return pixelsEffectedByBrush;
    }

    /**
     * Initiated everytime the mouse interact with the canvas. Returns the shape <pixelsEffectedByBrush> calculated
     * by calculateEffectedPixels on the centre of the coordinate where the mouse landed on.
     *
     * @param currentCanvas     The current canvas which the mouse is interacting with
     * @param currentColour     The current color chosen by the user
     * @param x                 The x coordinate of the user mouse input
     * @param y                 The y coordinate of the user mouse input
     * @return                  AN array collection of arrays of integer coordinates around the (x, y) coordinates in
     *                          the shape of pixelsEffectedByBrush
     */
    public int[][] draw(Project currentCanvas, Colour currentColour, int x, int y) {
        // Creating a new array to store the pixels that are updated in this method. These will then
        // Be sent back up to javafx to be rendered on the canvas.
        int[][] pixelsToUpdate = new int[pixelsEffectedByBrush.length][2];
        LayerManager layerManager = new LayerManager(currentCanvas);

        for (int offset = 0; offset < pixelsEffectedByBrush.length; offset++) {
            //Check if the pixels are in the canvas
            if (x + pixelsEffectedByBrush[offset][0] > 0
                    && x + pixelsEffectedByBrush[offset][0] < currentCanvas.width
                    && y + pixelsEffectedByBrush[offset][1] > 0 &&
                    y + pixelsEffectedByBrush[offset][1] < currentCanvas.height) {
                //If they are, update the updated pixels list and the canvas itself

                pixelsToUpdate[offset][0] = x + pixelsEffectedByBrush[offset][0];
                pixelsToUpdate[offset][1] = y + pixelsEffectedByBrush[offset][1];
                layerManager.getSelectedLayer().layerCanvas[x + pixelsEffectedByBrush[offset][0]]
                        [y + pixelsEffectedByBrush[offset][1]].setRGB(currentColour.getRGB());
            }
        }
        return pixelsToUpdate;
    }
}
