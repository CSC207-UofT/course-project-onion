package com.onionshop.entities;

public class Eraser extends Brush {

    /**
     * Construct an Eraser (child of Brush) that has the properties brush shape and brush size.
     *
     * @param brushShape String of the shape of the brush, all lower case - default round eraser
     * @param brushSize  int of the size of the brush, represents pixel size
     */
    public Eraser(String brushShape, int brushSize) {
        super("round", brushSize);
        this.calculateEffectedPixels();
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

    @Override public int[][] draw(Project currentCanvas, Colour currentColour, int x, int y, Layer layer) {
        return super.draw(currentCanvas, new Colour("white", new int[]{255, 255, 255}), x, y, layer);

    }
}
