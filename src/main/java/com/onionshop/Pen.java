package com.onionshop;

public class Pen extends Brush implements Tool{
    private String brushShape;
    private int brushSize;

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
    }

    @Override
    public void makeSelectable() {
        //TODO: will implement this later once we know exactly what's going on with JavaFX
    }

    @Override
    public void draw() {
        //TODO: will implement this later once we know exactly what's going on with JavaFX
    }
}
