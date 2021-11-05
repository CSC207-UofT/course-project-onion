package com.onionshop.entities;

public class Circle extends Shape implements Tool {

    public Circle(String shapeType, int brushSize) {
        super("circle", 1);
        this.calculateEffectedPixels();
    }

    public void calculateEffectedPixels() {
        pixelsEffectedByShape = new int[121][2];



    }

}
