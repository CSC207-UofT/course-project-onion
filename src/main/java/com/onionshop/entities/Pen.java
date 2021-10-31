package com.onionshop.entities;

public class Pen extends Brush implements Tool {
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

    public void makeSelectable() {
        //TODO: will implement this later once we know exactly what's going on with JavaFX
    }
}
