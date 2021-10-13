package com.onionshop;

public class Pen extends Brush{

    private String brushShape;
    private int brushSize;

    public Pen(String brushShape, int brushSize) {
        super(brushShape, brushSize);
        this.brushShape = "round";
        this.brushSize = 1;
    }
}
