package com.onionshop;

public class Eraser extends Brush{

    private String brushShape;
    private int brushSize;
    private int[][] pixelsEffectedByBrush;

    /**
     * Construct an Eraser (child of Brush) that has the properties brush shape and brush size.
     *
     * @param brushShape String of the shape of the brush, all lower case - default round eraser
     * @param brushSize  int of the size of the brush, represents pixel size
     */
    public Eraser(String brushShape, int brushSize) {
        super(brushShape, brushSize);
        this.brushShape = "round";
        this.brushSize = brushSize;
        this.calculateEffectedPixels();
    }

    /**
     * Calculate the pixels effected by the eraser.
     *
     */
    @Override public void calculateEffectedPixels() {
        pixelsEffectedByBrush = new int[121][2];
        int counter = 0;
        for (int x = -5; x < 6; x++) {
            for (int y = -5; y < 6; y++) {
                pixelsEffectedByBrush[counter][0] = x;
                pixelsEffectedByBrush[counter][1] = y;
                counter++;
            }
        }
    }

    /**
     * Draws the colour white onto the project canvas.
     *
     * @param currentCanvas the current canvas
     * @param currentColour the colour
     * @param x where the draw action should take place (x coordinate)
     * @param y where the draw action should take place (y coordinate)
     * @return pixels that are updating after the draw action.
     */

    @Override public int[][] draw(Project currentCanvas, Colour currentColour, int x, int y) {
        // Creating a new array to store the pixels that are updated in this method. These will then
        // TODO: Pretty similar to Pen, should probably use a parent class that both Pen and Eraser can use
        // Be sent back up to javafx to be rendered on the canvas.
        int[][] pixelsToUpdate = new int[pixelsEffectedByBrush.length][2];
        int[] white = {255,255,255};

        for (int offset = 0; offset < pixelsEffectedByBrush.length; offset++) {
            //Check if the pixels are in the canvas
            if (x + pixelsEffectedByBrush[offset][0] > 0 && x + pixelsEffectedByBrush[offset][0] < currentCanvas.width
                    && y + pixelsEffectedByBrush[offset][1] > 0 &&
                    y + pixelsEffectedByBrush[offset][1] < currentCanvas.height) {
                //If they are, update the updated pixels list and the canvas itself

                pixelsToUpdate[offset][0] = x + pixelsEffectedByBrush[offset][0];
                pixelsToUpdate[offset][1] = y + pixelsEffectedByBrush[offset][1];
                currentCanvas.drawingCanvas[x + pixelsEffectedByBrush[offset][0]]
                        [y + pixelsEffectedByBrush[offset][1]].setRGB(white);
            }
        }

        return pixelsToUpdate;
    }

}
