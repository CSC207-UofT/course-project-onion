package com.onionshop.entities;

public abstract class Shape implements Tool {
    private String shapeType;
    protected int[][] pixelsEffectedByShape;
    private int brushSize;

    public Shape(String shapeType, int brushSize) {
        this.shapeType = shapeType;
        this.brushSize = 1;
    }

    public String getShapeType() {
        return shapeType;
    }

    public int getBrushSize() {
        return brushSize;
    }

    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }

    public void setBrushSize(int brushSize) {
        this.brushSize = brushSize;
    }

    public int[][] draw(Project currentCanvas, Colour currentColour, int x, int y) {
        // Creating a new array to store the pixels that are updated in this method. These will then
        // Be sent back up to javafx to be rendered on the canvas.
        int[][] pixelsToUpdate = new int[pixelsEffectedByShape.length][2];

        for (int offset = 0; offset < pixelsEffectedByShape.length; offset++) {
            //Check if the pixels are in the canvas
            if (x + pixelsEffectedByShape[offset][0] > 0 && x + pixelsEffectedByShape[offset][0] < currentCanvas.width
                    && y + pixelsEffectedByShape[offset][1] > 0 &&
                    y + pixelsEffectedByShape[offset][1] < currentCanvas.height) {
                //If they are, update the updated pixels list and the canvas itself

                pixelsToUpdate[offset][0] = x + pixelsEffectedByShape[offset][0];
                pixelsToUpdate[offset][1] = y + pixelsEffectedByShape[offset][1];
                currentCanvas.drawingCanvas[x + pixelsEffectedByShape[offset][0]]
                        [y + pixelsEffectedByShape[offset][1]].setRGB(currentColour.getRGB());
            }
        }

        return pixelsToUpdate;
    }

    public void calculateEffectedPixels() {
        pixelsEffectedByShape = new int[121][2];

        int counter = 0;
        for (int x = -5; x < 6; x++) {
            for (int y = -5; y < 6; y++) {
                this.pixelsEffectedByShape[counter][0] = x;
                this.pixelsEffectedByShape[counter][1] = y;
                counter++;
            }
        }
    }

}
