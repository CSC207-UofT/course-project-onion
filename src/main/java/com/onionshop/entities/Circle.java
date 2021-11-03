package com.onionshop.entities;

public class Circle extends Shape {

    public Circle(String shapeType, int brushSize) {
        super("circle", 1);
        this.calculateEffectedPixels();
    }

}
