package com.onionshop.entities;

public abstract class Shape implements Tool {
    private String shapeType;
    protected int[][] pixelsEffectedByShape;
    private int brushSize;
    protected int[] startingCoordinate;
    protected int[] endingCoordinate;

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

    /**
     *  Calculates the distance between two pixels
     */
    public int[] calculateStartEndDistance() {
        int[] distances = new int[2];
        // the x distance between the two pixels
        int xDistance = startingCoordinate[0] - endingCoordinate[0];
        // the y distance between the two pixels
        int yDistance = startingCoordinate[1] - endingCoordinate[1];

        distances[0] = xDistance;
        distances[1] = yDistance;

        return distances;
    }

    public double calculateSlope() {
        // fixed line thickness for now
        int lineThickness = 1;

        int[] distances = calculateStartEndDistance();

        // the slope is rise/run
        double slope = distances[0] / distances[1];

        return slope;
    }

}
