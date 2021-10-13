package com.onionshop;

public abstract class Brush {
    private String brushShape;
    private int brushSize;

    /**
     * Construct a Brush that has the properties brush shape and brush size.
     *
     * @param brushShape    String of the shape of the brush, all lower case
     * @param brushSize     int of the size of the brush, represents pixel size
     */
    public Brush(String brushShape, int brushSize) {
        this.brushShape = brushShape;
        this.brushSize = 1;
    }

    /**
     * Returns the current SHAPE of the brush.
     *
     * @return      the current shape of the brush in String
     */
    public String getBrushShape() {
        return brushShape;
    }

    /**
     * Returns the current SIZE of the brush.
     *
     * @return      the current size of the brush in int
     */
    public int getBrushSize() {
        return brushSize;
    }

    /**
     * Sets the current SHAPE of the brush to an input string brushShape.
     *
     * @param brushShape    String of the shape of the brush, all lower case
     */
    public void setBrushShape(String brushShape) {
        this.brushShape = brushShape;
    }

    /**
     * Sets the current SIZE of the brush to an input string brushSize.
     *
     * @param brushSize     String of the size of the brush, represents pixel size
     */
    public void setBrushSize(int brushSize) {
        this.brushSize = brushSize;
    }
}
