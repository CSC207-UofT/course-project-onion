package com.onionshop;

public abstract class Brush {

    /**
     * Private instance variables
     *      - a String named brushShape
     *      - an int named brushSize
     **/

    private String brushShape;
    private int brushSize;

    public Brush(String brushShape, int brushSize) {
        this.brushShape = brushShape;
        this.brushSize = 1;
    }

    public String getBrushShape() {
        return brushShape;
    }

    public int getBrushSize() {
        return brushSize;
    }

    public void setBrushShape(String brushShape) {
        this.brushShape = brushShape;
    }

    public void setBrushSize(int brushSize) {
        this.brushSize = brushSize;
    }
}
