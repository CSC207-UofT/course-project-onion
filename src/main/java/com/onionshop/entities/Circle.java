package com.onionshop.entities;

public class Circle extends Shape implements Tool {

    public Circle(int brushSize) {
        super(1);
        this.calculateEffectedPixels();
    }

    public void calculateEffectedPixels() {
        pixelsEffectedByShape = new int[121][2];



    }

}
