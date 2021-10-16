package com.onionshop;

import java.util.ArrayList;
import java.util.Arrays;

public class Pen extends Brush implements Tool{
    private String brushShape;
    private int brushSize;
    private int[][] pixelsEffectedByBrush;

    /**
     * Construct a Pen (Child of class Brush) that has the properties brush shape
     * that is round by default, and brush size that is 1 by default.
     *
     * @param brushShape    String of the shape of the brush, all lower case, default is round
     * @param brushSize     int of the size of the brush, represents pixel size, default is 1 (px)
     */
    public Pen(String brushShape, int brushSize) {
        super(brushShape, brushSize);
        this.brushShape = "round";
        this.brushSize = 1;
        this.calculateEffectedPixels();
    }

    public void calculateEffectedPixels() {
        //Hardcoding a small offset sphere just for phase 0. Code for calculating the circle is below for future phases

        pixelsEffectedByBrush = new int[121][2];
        /*
        pixelsEffectedByBrush[0][0] = 0;
        pixelsEffectedByBrush[0][1] = 1;

        pixelsEffectedByBrush[1][0] = 1;
        pixelsEffectedByBrush[1][1] = 0;

        pixelsEffectedByBrush[2][0] = 0;
        pixelsEffectedByBrush[2][1] = -1;

        pixelsEffectedByBrush[3][0] = -1;
        pixelsEffectedByBrush[3][1] = 0;

        pixelsEffectedByBrush[4][0] = 0;
        pixelsEffectedByBrush[4][1] = 0;
        */
        int counter = 0;
        for (int x = -5; x < 6; x++) {
            for (int y = -5; y < 6; y++) {
                pixelsEffectedByBrush[counter][0] = x;
                pixelsEffectedByBrush[counter][1] = y;
                counter++;
            }
        }


        /*
        This is code for later. This isn't currently working
        double radius = Math.pow(brushSize / Math.PI, 0.5);
        int[][] potentialPixels = new int[(int)radius*2 + 2][(int)radius*2 + 2];
        int currentArrayInd = 0;

        for (int x = 0; x < potentialPixels.length; x++) {
            for (int y = 0; y < potentialPixels.length; y++) {
                if (Math.pow(x - radius, 2) + Math.pow(y - radius, 2) <= Math.pow(radius, 2)) {
                }
            }
        } */
    }

    public void makeSelectable() {
        //TODO: will implement this later once we know exactly what's going on with JavaFX
    }

    public int[][] draw(Project currentCanvas, Colour currentColour, int x, int y) {
        // Creating a new array to store the pixels that are updated in this method. These will then
        // Be sent back up to javafx to be rendered on the canvas.
        int[][] pixelsToUpdate = new int[pixelsEffectedByBrush.length][2];

        for (int offset = 0; offset < pixelsEffectedByBrush.length; offset++) {
            //Check if the pixels are in the canvas
            if (x + pixelsEffectedByBrush[offset][0] > 0 && x + pixelsEffectedByBrush[offset][0] < currentCanvas.width
                    && y + pixelsEffectedByBrush[offset][1] > 0 &&
                    y + pixelsEffectedByBrush[offset][1] < currentCanvas.height) {
                //If they are, update the updated pixels list and the canvas itself

                pixelsToUpdate[offset][0] = x + pixelsEffectedByBrush[offset][0];
                pixelsToUpdate[offset][1] = y + pixelsEffectedByBrush[offset][1];
                currentCanvas.drawingCanvas[x + pixelsEffectedByBrush[offset][0]]
                        [y + pixelsEffectedByBrush[offset][1]].setRGB(currentColour.getRGB());
            }
        }

        return pixelsToUpdate;
    }
}
