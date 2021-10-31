package com.onionshop.entities;

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

    @Override
    public void makeSelectable() {
        // TODO: Needs to be implemented
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
