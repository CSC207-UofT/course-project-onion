package com.onionshop.entities;

public class Rectangle extends Shape implements Tool {

    public Rectangle(int brushSize) {
        super(1);
        this.calculateEffectedPixels();
    }

    public void calculateEffectedPixels() {
        pixelsEffectedByShape = new int[121][2];



    }

}
