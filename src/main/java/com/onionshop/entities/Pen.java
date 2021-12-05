package com.onionshop.entities;

public class Pen extends Brush {
    /**
     * Construct a Pen (Child of class Brush) that has the properties brush shape
     * that is round by default, and brush size that is 1 by default.
     *
     * @param brushShape    String of the shape of the brush, all lower case, default is round
     * @param brushSize     int of the size of the brush, represents pixel size, default is 1 (px)
     */
    public Pen(String brushShape, int brushSize) {
        super(brushShape, brushSize);
        this.calculateEffectedPixels();
    }

    @Override
    public int[][] draw(Layer currentCanvas, Colour currentColour, int x, int y) {
        return new int[0][];
    }
}
